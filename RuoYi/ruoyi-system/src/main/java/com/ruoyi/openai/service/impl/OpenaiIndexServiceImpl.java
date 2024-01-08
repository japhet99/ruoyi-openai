package com.ruoyi.openai.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.req.ContextMessage;
import com.ruoyi.common.resp.ChatGPTResp;
import com.ruoyi.common.utils.ImageUtil;
import com.ruoyi.common.utils.MilvusClientUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.openai.async.SocketAsync;
import com.ruoyi.openai.domain.*;
import com.ruoyi.openai.listener.ConsoleEventSourceListener;
import com.ruoyi.openai.service.*;
import com.ruoyi.system.service.ISysConfigService;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class OpenaiIndexServiceImpl implements IOpenaiIndexService {

    public final Logger logger = LoggerFactory.getLogger(OpenaiIndexServiceImpl.class);

    @Autowired
    private IOpenaiPromptsTypeService openaiPromptsTypeService;
    @Autowired
    private IOpenaiChatService openaiChatService;
    @Autowired
    private IOpenaiPromptsService openaiPromptsService;
    @Autowired
    private SocketIOService socketIOService;
    @Autowired
    private IOpenaiUserInfoService openaiUserInfoService;
    @Autowired
    private IOpenaiChatDetailService openaiChatDetailService;
    @Autowired
    private SocketAsync socketAsync;
    @Autowired
    private IOpenaiDalleService openaiDalleService;
    @Autowired
    private ISysConfigService configService;

    /**
     * 获取菜单
     * @param userId
     * @param title
     * @return
     */
    @Override
    public AjaxResult getMenu(Long userId,String title) {

        Map<String,Object> map = new HashMap<>();


        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(userId);

        OpenaiPromptsType openaiPromptsType = new OpenaiPromptsType();
        openaiPromptsType.setCompanyId(openaiUserInfo1.getCompanyId());

        String orderBy2 = "sort desc";
        PageHelper.orderBy(orderBy2);
        List<OpenaiPromptsType> openaiPromptsTypes = openaiPromptsTypeService.selectOpenaiPromptsTypeList(openaiPromptsType);

        for (OpenaiPromptsType promptsType : openaiPromptsTypes) {
            if(promptsType.getType()==1){
                //3.5特殊处理
                List<Map<String, Object>> openaiChatList35 = openaiChatService.selectMyChat(userId, promptsType.getId());
                List<OpenaiPrompts> list = new ArrayList<>();

                OpenaiPrompts openaiPrompts3 = new OpenaiPrompts();
                openaiPrompts3.setCompanyId(openaiUserInfo1.getCompanyId());
                openaiPrompts3.setTId(promptsType.getId());
                List<OpenaiPrompts> list1 = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts3);
                OpenaiPrompts openaiPrompts = list1.get(0);

                // 3.5 新增的按钮
                OpenaiPrompts openaiPrompts2 = new OpenaiPrompts();
                openaiPrompts2.setTitle("创建新会话");
                openaiPrompts2.setContent("");
                openaiPrompts2.setIcon(openaiPrompts.getIcon());
                openaiPrompts2.setUuid("add35");
                openaiPrompts2.setId(openaiPrompts.getId());
                list.add(openaiPrompts2);


                if(openaiChatList35.size()==0){
                    openaiPrompts.setContent("");
                    openaiPrompts.setUuid(UUID.randomUUID().toString());
                    list.add(openaiPrompts);
                }else{
                    for (Map<String, Object> stringObjectMap : openaiChatList35) {
                        openaiPrompts = new OpenaiPrompts();
                        openaiPrompts.setId(Long.parseLong(stringObjectMap.get("id").toString()));
                        openaiPrompts.setUuid(stringObjectMap.get("uuid").toString());
                        openaiPrompts.setTitle(stringObjectMap.get("title").toString());
                        openaiPrompts.setContent(stringObjectMap.get("content").toString());
                        openaiPrompts.setFirstContent(stringObjectMap.get("firstContent").toString());
                        openaiPrompts.setIcon(stringObjectMap.get("icon").toString());
                        list.add(openaiPrompts);
                    }
                }

                promptsType.setOpenaiPromptsList(list);
            }else if(promptsType.getType()==2){

                //4.0特殊处理
                List<Map<String, Object>> openaiChatList40 = openaiChatService.selectMyChat(userId, promptsType.getId());
                List<OpenaiPrompts> list = new ArrayList<>();


                OpenaiPrompts openaiPrompts3 = new OpenaiPrompts();
                openaiPrompts3.setCompanyId(openaiUserInfo1.getCompanyId());
                openaiPrompts3.setTId(promptsType.getId());
                List<OpenaiPrompts> list1 = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts3);
                OpenaiPrompts openaiPrompts = list1.get(0);

                // 4.0 新增的按钮
                OpenaiPrompts openaiPrompts2 = new OpenaiPrompts();
                openaiPrompts2.setTitle("创建新会话");
                openaiPrompts2.setIcon(openaiPrompts.getIcon());
                openaiPrompts2.setContent("");
                openaiPrompts2.setUuid("add40");
                openaiPrompts2.setId(openaiPrompts.getId());
                list.add(openaiPrompts2);

                if(openaiChatList40.size()==0){
                    openaiPrompts.setContent("");
                    openaiPrompts.setUuid(UUID.randomUUID().toString());
                    list.add(openaiPrompts);
                }else{
                    for (Map<String, Object> stringObjectMap : openaiChatList40) {
                        openaiPrompts = new OpenaiPrompts();
                        openaiPrompts.setId(Long.parseLong(stringObjectMap.get("id").toString()));
                        openaiPrompts.setUuid(stringObjectMap.get("uuid").toString());
                        openaiPrompts.setTitle(stringObjectMap.get("title").toString());
                        openaiPrompts.setContent(stringObjectMap.get("content").toString());
                        openaiPrompts.setFirstContent(stringObjectMap.get("firstContent").toString());
                        openaiPrompts.setIcon(stringObjectMap.get("icon").toString());
                        list.add(openaiPrompts);
                    }
                }

                promptsType.setOpenaiPromptsList(list);

            }else if(promptsType.getType()==3){

                //pdf 特殊处理
                List<Map<String, Object>> openaiChatList35 = openaiChatService.selectMyChat(userId, promptsType.getId());
                List<OpenaiPrompts> list = new ArrayList<>();

                OpenaiPrompts openaiPrompts3 = new OpenaiPrompts();
                openaiPrompts3.setCompanyId(openaiUserInfo1.getCompanyId());
                openaiPrompts3.setTId(promptsType.getId());
                List<OpenaiPrompts> list1 = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts3);
                OpenaiPrompts openaiPrompts = list1.get(0);

                // pdf 新增的按钮
                OpenaiPrompts openaiPrompts2 = new OpenaiPrompts();
                openaiPrompts2.setTitle("创建新会话");
                openaiPrompts2.setContent("");
                openaiPrompts2.setIcon(openaiPrompts.getIcon());
                openaiPrompts2.setUuid("addpdf");
                openaiPrompts2.setId(promptsType.getId());
                list.add(openaiPrompts2);


                if(openaiChatList35.size()==0){
                    openaiPrompts.setContent("");
                    openaiPrompts.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
                    list.add(openaiPrompts);
                }else{
                    for (Map<String, Object> stringObjectMap : openaiChatList35) {
                        openaiPrompts = new OpenaiPrompts();
                        openaiPrompts.setId(Long.parseLong(stringObjectMap.get("id").toString()));
                        openaiPrompts.setUuid(stringObjectMap.get("uuid").toString());
                        openaiPrompts.setTitle(stringObjectMap.get("title").toString());
                        openaiPrompts.setContent(stringObjectMap.get("content").toString());
                        openaiPrompts.setFirstContent(stringObjectMap.get("firstContent").toString());
                        openaiPrompts.setIcon(stringObjectMap.get("icon").toString());
                        list.add(openaiPrompts);
                    }
                }

                promptsType.setOpenaiPromptsList(list);

            }else if(promptsType.getType()==5){
                //AI识图  特殊处理
                List<Map<String, Object>> openaiChatList35 = openaiChatService.selectMyChat(userId, promptsType.getId());
                List<OpenaiPrompts> list = new ArrayList<>();

                OpenaiPrompts openaiPrompts3 = new OpenaiPrompts();
                openaiPrompts3.setCompanyId(openaiUserInfo1.getCompanyId());
                openaiPrompts3.setTId(promptsType.getId());
                List<OpenaiPrompts> list1 = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts3);
                OpenaiPrompts openaiPrompts = list1.get(0);

                // pdf 新增的按钮
                OpenaiPrompts openaiPrompts2 = new OpenaiPrompts();
                openaiPrompts2.setTitle("创建新会话");
                openaiPrompts2.setContent("");
                openaiPrompts2.setIcon(openaiPrompts.getIcon());
                openaiPrompts2.setUuid("addAiPicture");
                openaiPrompts2.setId(openaiPrompts.getId());
                list.add(openaiPrompts2);


                if(openaiChatList35.size()==0){
                    openaiPrompts.setContent("");
                    openaiPrompts.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
                    list.add(openaiPrompts);
                }else{
                    for (Map<String, Object> stringObjectMap : openaiChatList35) {
                        openaiPrompts = new OpenaiPrompts();
                        openaiPrompts.setId(Long.parseLong(stringObjectMap.get("id").toString()));
                        openaiPrompts.setUuid(stringObjectMap.get("uuid").toString());
                        openaiPrompts.setTitle(stringObjectMap.get("title").toString());
                        openaiPrompts.setContent(stringObjectMap.get("content").toString());
                        openaiPrompts.setFirstContent(stringObjectMap.get("firstContent").toString());
                        openaiPrompts.setIcon(stringObjectMap.get("icon").toString());
                        list.add(openaiPrompts);
                    }
                }

                promptsType.setOpenaiPromptsList(list);

            }else if(promptsType.getType()==6){
                //AI联网  特殊处理
                List<Map<String, Object>> openaiChatList35 = openaiChatService.selectMyChat(userId, promptsType.getId());
                List<OpenaiPrompts> list = new ArrayList<>();

                OpenaiPrompts openaiPrompts3 = new OpenaiPrompts();
                openaiPrompts3.setCompanyId(openaiUserInfo1.getCompanyId());
                openaiPrompts3.setTId(promptsType.getId());
                List<OpenaiPrompts> list1 = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts3);
                OpenaiPrompts openaiPrompts = list1.get(0);

                // 新增的按钮
                OpenaiPrompts openaiPrompts2 = new OpenaiPrompts();
                openaiPrompts2.setTitle("创建新会话");
                openaiPrompts2.setContent("");
                openaiPrompts2.setIcon(openaiPrompts.getIcon());
                openaiPrompts2.setUuid("addNetwork");
                openaiPrompts2.setId(openaiPrompts.getId());
                list.add(openaiPrompts2);


                if(openaiChatList35.size()==0){
                    openaiPrompts.setContent("");
                    openaiPrompts.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
                    list.add(openaiPrompts);
                }else{
                    for (Map<String, Object> stringObjectMap : openaiChatList35) {
                        openaiPrompts = new OpenaiPrompts();
                        openaiPrompts.setId(Long.parseLong(stringObjectMap.get("id").toString()));
                        openaiPrompts.setUuid(stringObjectMap.get("uuid").toString());
                        openaiPrompts.setTitle(stringObjectMap.get("title").toString());
                        openaiPrompts.setContent(stringObjectMap.get("content").toString());
                        openaiPrompts.setFirstContent(stringObjectMap.get("firstContent").toString());
                        openaiPrompts.setIcon(stringObjectMap.get("icon").toString());
                        list.add(openaiPrompts);
                    }
                }

                promptsType.setOpenaiPromptsList(list);

            }else{
                //自定义模型
                OpenaiPrompts openaiPrompts = new OpenaiPrompts();
                openaiPrompts.setStatus(0);
                openaiPrompts.setTId(promptsType.getId());
//                openaiPrompts.setTitle(title);
                String orderBy = "sort desc";
                PageHelper.orderBy(orderBy);
                List<OpenaiPrompts> openaiPrompts1 = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts);
                for (OpenaiPrompts prompts : openaiPrompts1) {
                    prompts.setPrompts("");
                    OpenaiChat openaiChat = new OpenaiChat();
                    openaiChat.setUserId(userId);
                    openaiChat.setMId(prompts.getId());
                    openaiChat.setStatus(0);
                    List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat);
                    if(openaiChats.size()==1){
                        prompts.setUuid(openaiChats.get(0).getUuid());
                        prompts.setContent(openaiChats.get(0).getTitle());
                        prompts.setCreateTime(openaiChats.get(0).getCreateTime());
                    }else{
                        prompts.setUuid(UUID.randomUUID().toString());
                        prompts.setContent(prompts.getFirstContent());
                        prompts.setCreateTime(new Date());
                    }
                }
                promptsType.setOpenaiPromptsList(openaiPrompts1);
            }
        }

        map.put("menu",openaiPromptsTypes);
        return AjaxResult.success(map);
    }


    /**
     * 3.5 模型聊天
     * @param prompt
     * @param uuid
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult chat35(String prompt, String uuid, Long userId,Long mId) {

        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);
        if(prompt == null || prompt.trim().length()<=0){
            SendEndMsg("请输入内容!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }else if(prompt.length()>openaiPrompts.getMaxTokens()){
            SendEndMsg("内容太长,请精简提问!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }

        //判断用户的免费次数是否用完
        OpenaiUserInfo openaiUserInfo = new OpenaiUserInfo();
        openaiUserInfo.setUserId(userId);
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoList(openaiUserInfo).get(0);
        Long companyId = openaiUserInfo1.getCompanyId();
        //免费次数
        if (openaiUserInfo1.getFreeTime() <= 0) {
            logger.info("免费次数已用完");
            if (openaiUserInfo1.getExpirationTime() != null && openaiUserInfo1.getRechargeTime() != null) {
                if (openaiUserInfo1.getExpirationTime().before(new Date())) {
                    if (openaiUserInfo1.getMoneyTime() <= 0) {

                        SendEndMsg("客官,会员已过期!明天再来使用免费次数或 <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompt,uuid,mId,companyId);

                        return AjaxResult.success();

                    } else {
                        //减moneyTime
//                        int row = openaiUserInfoService.updateOpenaiUserInfoByMoneyTime(userId,-1);
//                        logger.info("减moneyTime" + row);

                        OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
                        openaiChatDetail.setContent(prompt);
                        openaiChatDetail.setRole(Message.Role.USER.getName());
                        openaiChatDetail.setUuid(uuid);
                        openaiChatDetail.setUserId(userId);
                        openaiChatDetail.setMId(mId);
                        openaiChatDetail.setCompanyId(companyId);
                        openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

                    }

                } else {
                    if (openaiUserInfo1.getMoneyTime() <= 0) {
//                        购买会员的次数用完 ,但是会员没有到期

                        OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
                        openaiChatDetail.setContent(prompt);
                        openaiChatDetail.setRole(Message.Role.USER.getName());
                        openaiChatDetail.setUuid(uuid);
                        openaiChatDetail.setUserId(userId);
                        openaiChatDetail.setMId(mId);
                        openaiChatDetail.setCompanyId(companyId);
                        openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

                    } else {
                        //减moneyTime
//                        int row = openaiUserInfoService.updateOpenaiUserInfoByMoneyTime(userId,-1);
//                        logger.info("减moneyTime" + row);

                        OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
                        openaiChatDetail.setContent(prompt);
                        openaiChatDetail.setRole(Message.Role.USER.getName());
                        openaiChatDetail.setUuid(uuid);
                        openaiChatDetail.setUserId(userId);
                        openaiChatDetail.setMId(mId);
                        openaiChatDetail.setCompanyId(companyId);
                        openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

                    }
                }
            } else {
                SendEndMsg("今日免费次数已用完,明天再来使用免费次数或 <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompt,uuid,mId,companyId);

                return AjaxResult.success();
            }
        } else {
            //减freeTime
//            int row = openaiUserInfoService.updateOpenaiUserInfoByFreeTime(userId,-1);
//            logger.info("减freeTime" + row);

            OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent(prompt);
            openaiChatDetail.setRole(Message.Role.USER.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(userId);
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

        }

        OpenaiChat openaiChat2 = new OpenaiChat();
        openaiChat2.setUuid(uuid);
        openaiChat2.setMId(mId);
        List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat2);

        List<Message> messageList = new ArrayList<>();

        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts.getTId());
        ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener(socketIOService, openaiChatDetailService,openaiUserInfoService, userId.toString(), uuid,mId,openaiPromptsType.getType());
        //保存聊天记录
        if (openaiChats.size() == 0) {
            openaiChats = new ArrayList<>();
            //新窗口
            OpenaiChat openaiChat = new OpenaiChat();
            openaiChat.setTitle(prompt);
            openaiChat.setUuid(uuid);
            openaiChat.setUserId(userId);
            openaiChat.setMId(mId);
            openaiChat.setCompanyId(companyId);
            openaiChatService.insertOpenaiChat(openaiChat);

            Message message = Message.builder().role(Message.Role.USER).content(prompt).build();
            messageList.add(message);

        } else {

            OpenaiChatDetail openaiChatDetail2 = new OpenaiChatDetail();
            openaiChatDetail2.setUuid(uuid);
            openaiChatDetail2.setStatus(0);
            PageHelper.orderBy("id desc");
            PageHelper.startPage(1, 6 );
            List<OpenaiChatDetail> openaiChatDetails = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail2);
            Collections.reverse(openaiChatDetails);
            for (OpenaiChatDetail chatDetail : openaiChatDetails) {
                Message message1 = new Message();
                message1.setRole(chatDetail.getRole());
                message1.setContent(chatDetail.getContent());
                messageList.add(message1);
            }
        }

        ChatCompletion chatCompletion = ChatCompletion.builder().model(openaiPrompts.getModel()).messages(messageList).build();
        socketAsync.chatGpt(openaiUserInfo1.getCompanyId(),eventSourceListener, chatCompletion);

        return AjaxResult.success();
    }


    /**
     * ChatGPT4 模型聊天
     * @param prompt
     * @param uuid
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult chat4(String prompt, String uuid, Long userId,Long mId) {

        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);

        if(prompt == null || prompt.trim().length()<=0){
            SendEndMsg("请输入内容!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }else if(prompt.length()>openaiPrompts.getMaxTokens()){
            SendEndMsg("内容太长,请精简提问!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }

        //判断用户的免费次数是否用完
        OpenaiUserInfo openaiUserInfo = new OpenaiUserInfo();
        openaiUserInfo.setUserId(userId);
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoList(openaiUserInfo).get(0);
        Long companyId = openaiUserInfo1.getCompanyId();
        //免费次数
        if (openaiUserInfo1.getMoneyTimeFour() <= 0) {

            SendEndMsg("客官,ChatGPT4.0 次数已用完! <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompt,uuid,mId,companyId);

            return AjaxResult.success();
        } else {
            //减moneyTime
//            int row = openaiUserInfoService.updateOpenaiUserInfoByMoneyTimeFour(userId,-1);
//            logger.info("减moneyTime" + row);

            OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent(prompt);
            openaiChatDetail.setRole(Message.Role.USER.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(userId);
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

        }

        OpenaiChat openaiChat2 = new OpenaiChat();
        openaiChat2.setUuid(uuid);
        openaiChat2.setMId(mId);
        List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat2);

        List<Message> messageList = new ArrayList<>();

        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts.getTId());
        ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener(socketIOService, openaiChatDetailService,openaiUserInfoService, userId.toString(), uuid,mId,openaiPromptsType.getType());
        //保存聊天记录
        if (openaiChats.size() == 0) {
            openaiChats = new ArrayList<>();
            //新窗口
            OpenaiChat openaiChat = new OpenaiChat();
            openaiChat.setTitle(prompt);
            openaiChat.setUuid(uuid);
            openaiChat.setUserId(userId);
            openaiChat.setMId(mId);
            openaiChat.setCompanyId(companyId);
            openaiChatService.insertOpenaiChat(openaiChat);

            Message message = Message.builder().role(Message.Role.USER).content(prompt).build();
            messageList.add(message);

        } else {

            OpenaiChatDetail openaiChatDetail2 = new OpenaiChatDetail();
            openaiChatDetail2.setUuid(uuid);
            openaiChatDetail2.setStatus(0);
            PageHelper.orderBy("id desc");
            PageHelper.startPage(1, 6 );
            List<OpenaiChatDetail> openaiChatDetails = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail2);
            Collections.reverse(openaiChatDetails);
            for (OpenaiChatDetail chatDetail : openaiChatDetails) {
                Message message1 = new Message();
                message1.setRole(chatDetail.getRole());
                message1.setContent(chatDetail.getContent());
                messageList.add(message1);
            }
        }

        ChatCompletion chatCompletion = ChatCompletion.builder().model(openaiPrompts.getModel()).messages(messageList).build();
        socketAsync.chatGpt4(openaiUserInfo1.getCompanyId(),eventSourceListener, chatCompletion);

        return AjaxResult.success();
    }


    /**
     * 自定义角色模型
     * @param prompt
     * @param uuid
     * @param mId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult chatRole(String prompt, String uuid, Long mId, Long userId) {

        OpenaiPrompts openaiPrompts2 = openaiPromptsService.selectOpenaiPromptsById(mId);
        if(openaiPrompts2==null){
            SendEndMsg("角色错误，请重试!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }

        if(prompt == null || prompt.trim().length()<=0){
            SendEndMsg("请输入内容!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }else if(prompt.length()>openaiPrompts2.getMaxTokens()){
            SendEndMsg("内容太长,请精简提问!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }

        //判断用户的免费次数是否用完
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(userId);
        Long companyId = openaiUserInfo1.getCompanyId();
        //免费次数
        if (openaiUserInfo1.getFreeTime() <= 0) {
            logger.info("免费次数已用完");
            if (openaiUserInfo1.getExpirationTime() != null && openaiUserInfo1.getRechargeTime() != null) {
                if (openaiUserInfo1.getExpirationTime().before(new Date())) {

                    if (openaiUserInfo1.getMoneyTime() <= 0) {

                        SendEndMsg("客官,会员已过期! 明天再来使用免费次数或 <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompt,uuid,mId,companyId);

                        return AjaxResult.success();

                    }else{

                        //减moneyTime
//                        int row = openaiUserInfoService.updateOpenaiUserInfoByMoneyTime(userId,-1);
//                        logger.info("减moneyTime" + row);

                        OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
                        openaiChatDetail.setContent(prompt);
                        openaiChatDetail.setRole(Message.Role.USER.getName());
                        openaiChatDetail.setUuid(uuid);
                        openaiChatDetail.setUserId(userId);
                        openaiChatDetail.setMId(mId);
                        openaiChatDetail.setCompanyId(companyId);
                        openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

                    }


                } else {
                    //减moneyTime
//                    int row = openaiUserInfoService.updateOpenaiUserInfoByMoneyTime(userId,-1);

                    OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
                    openaiChatDetail.setContent(prompt);
                    openaiChatDetail.setRole(Message.Role.USER.getName());
                    openaiChatDetail.setUuid(uuid);
                    openaiChatDetail.setUserId(userId);
                    openaiChatDetail.setMId(mId);
                    openaiChatDetail.setCompanyId(companyId);
                    openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);
                }
            } else {

                SendEndMsg("今日免费次数已用完,明天再来使用免费次数或 <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompt,uuid,mId,companyId);

                return AjaxResult.success();
            }
        } else {
            //减freeTime
//            int row = openaiUserInfoService.updateOpenaiUserInfoByFreeTime(userId,-1);
//            logger.info("减freeTime" + row);

            OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent(prompt);
            openaiChatDetail.setRole(Message.Role.USER.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(userId);
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

        }

        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);

        if(openaiPrompts==null){

            SendEndMsg("模型错误!",userId.toString(),false,null,null,null,null);

            return AjaxResult.success();
        }

        OpenaiChat openaiChat2 = new OpenaiChat();
        openaiChat2.setUuid(uuid);
        openaiChat2.setMId(mId);
        List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat2);

        List<Message> messageList = new ArrayList<>();

        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts.getTId());
        ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener(socketIOService, openaiChatDetailService,openaiUserInfoService, userId.toString(), uuid,mId,openaiPromptsType.getType());

        //保存聊天记录
        if (openaiChats.size() == 0) {
            openaiChats = new ArrayList<>();
            //新窗口
            OpenaiChat openaiChat = new OpenaiChat();
            openaiChat.setTitle(prompt);
            openaiChat.setUuid(uuid);
            openaiChat.setUserId(userId);
            openaiChat.setMId(mId);
            openaiChat.setCompanyId(companyId);
            openaiChatService.insertOpenaiChat(openaiChat);

            Message message = Message.builder().role(Message.Role.SYSTEM).content(openaiPrompts.getPrompts()).build();
            Message message2 = Message.builder().role(Message.Role.USER).content(prompt).build();
            messageList.add(message);
            messageList.add(message2);

        } else {

            OpenaiChatDetail openaiChatDetail2 = new OpenaiChatDetail();
            openaiChatDetail2.setUuid(uuid);
            openaiChatDetail2.setStatus(0);
            PageHelper.orderBy("id desc");
            PageHelper.startPage(1, 6 );
            List<OpenaiChatDetail> openaiChatDetails = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail2);
            Collections.reverse(openaiChatDetails);

            Message message = Message.builder().role(Message.Role.SYSTEM).content(openaiPrompts.getPrompts()).build();
            messageList.add(message);

            for (OpenaiChatDetail chatDetail : openaiChatDetails) {
                Message message1 = new Message();
                message1.setRole(chatDetail.getRole());
                message1.setContent(chatDetail.getContent());
                messageList.add(message1);
            }
        }

        ChatCompletion chatCompletion = ChatCompletion.builder().model(openaiPrompts.getModel()).messages(messageList).build();
        socketAsync.chatGpt(openaiUserInfo1.getCompanyId(),eventSourceListener, chatCompletion);

        return AjaxResult.success();
    }

    /**
     *
     * @param openaiChatDetail
     * @param userId
     * @return
     */
    @Override
    public AjaxResult deleteChatList(OpenaiChatDetail openaiChatDetail,Long userId) {
        OpenaiChat openaiChat = new OpenaiChat();
        openaiChat.setMId(openaiChatDetail.getMId());
        openaiChat.setUuid(openaiChatDetail.getUuid());
        openaiChat.setStatus(0);
        List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat);
        for (OpenaiChat chat : openaiChats) {
            chat.setStatus(1);
            openaiChatService.updateOpenaiChat(chat);
        }
        OpenaiChatDetail openaiChatDetail2 = new OpenaiChatDetail();
        openaiChatDetail2.setMId(openaiChatDetail.getMId());
        openaiChatDetail2.setUuid(openaiChatDetail.getUuid());
        openaiChatDetail2.setStatus(0);
        List<OpenaiChatDetail> openaiChatDetails = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail2);
        for (OpenaiChatDetail chatDetail : openaiChatDetails) {
            chatDetail.setStatus(1);
            openaiChatDetailService.updateOpenaiChatDetail(chatDetail);
        }
        String collectionName = "id_"+userId+ "_" + openaiChatDetail.getUuid();
        if(MilvusClientUtil.hasCollection(collectionName)){
            MilvusClientUtil.releaseCollection(collectionName);
        }
        return AjaxResult.success();
    }

    /**
     *
     * @param openaiDalle
     * @param userId
     * @return
     */
    @Override
    public AjaxResult dallE3(OpenaiDalle openaiDalle, Long userId) {

        if(openaiDalle.getModel()==1){
            if(openaiDalle.getSize()!=1 && openaiDalle.getSize()!=2 && openaiDalle.getSize()!=3){
                return AjaxResult.error("size错误");
            }
            if(openaiDalle.getN()<=0 || openaiDalle.getN()>=11){
                return AjaxResult.error("绘画数量错误，1~10之间");
            }
            if(openaiDalle.getPrompt().length()<=0){
                return AjaxResult.error("内容不能为空");
            }
            if(openaiDalle.getPrompt().length()>=800){
                return AjaxResult.error("内容太长了");
            }
        }else if(openaiDalle.getModel()==2){
            if(openaiDalle.getSize()!=3 && openaiDalle.getSize()!=4 && openaiDalle.getSize()!=5){
                return AjaxResult.error("size错误");
            }
            if(openaiDalle.getN()!=1){
                openaiDalle.setN(1L);
            }
            if(openaiDalle.getPrompt().length()<=0){
                return AjaxResult.error("内容不能为空");
            }
            if(openaiDalle.getPrompt().length()>=2000){
                return AjaxResult.error("内容太长了");
            }
        }else{
            return AjaxResult.error("模型错误");
        }
        if(openaiDalle.getPrompt().length()<=0){
            return AjaxResult.error("内容不能为空");
        }
        OpenaiDalleSub openaiDalleSub = new OpenaiDalleSub();

        //模型
        switch (openaiDalle.getModel()){
            case 1:
                openaiDalleSub.setModel("dall-e-2");
                break;
            case 2:
                openaiDalleSub.setModel("dall-e-3");
                break;
        }
        //像素
        switch (openaiDalle.getSize()){
            case 1:
                openaiDalleSub.setSize("256x256");
                break;
            case 2:
                openaiDalleSub.setSize("512x512");
                break;
            case 3:
                openaiDalleSub.setSize("1024x1024");
                break;
//            case 4:
//                openaiDalleSub.setSize("1792x1024");
//                break;
//            case 5:
//                openaiDalleSub.setSize("1024x1792");
//                break;
            default:
                openaiDalleSub.setSize("1024x1024");
                break;
        }
        //风格
//        switch (openaiDalle.getStyle()){
//            case 1:
//                openaiDalleSub.setStyle("vivid");
//                break;
//            case 2:
//                openaiDalleSub.setStyle("natural");
//                break;
//        }
        openaiDalleSub.setN(openaiDalle.getN().intValue());
        openaiDalleSub.setPrompt(openaiDalle.getPrompt());

        //判断用户的免费次数是否用完
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(userId);
        String localUrl = configService.selectConfigByKeyAndCompanyId(openaiUserInfo1.getCompanyId(),"local_url").replace("/login","");
        if(openaiUserInfo1.getDallETime()<openaiDalleSub.getN()){
            return AjaxResult.error("次数不够啦~");
        }

        Long companyId = openaiUserInfo1.getCompanyId();
//        String dallE3 = socketAsync.dallE3(companyId, openaiDalleSub);
        String dallE3 = socketAsync.dallE3S(companyId, openaiDalleSub);
        if(dallE3==null){
            return AjaxResult.error("网络异常,请稍后重试");
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(dallE3);
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            OpenaiDalle openaiDalle2 = new OpenaiDalle();
            openaiDalle2.setPrompt(openaiDalle.getPrompt());
            openaiDalle2.setN(openaiDalle.getN());
            openaiDalle2.setUserId(userId);
            openaiDalle2.setModel(openaiDalle.getModel());
            openaiDalle2.setSize(openaiDalle.getSize());
            openaiDalle2.setCompanyId(companyId);
            openaiDalle2.setStyle(openaiDalle.getStyle());
            openaiDalle2.setCreateTime(new Date());
            JSONObject parse = (JSONObject)JSONObject.parse(data.get(i).toString());
            String downUrl = ImageUtil.downUrl(parse.getString("url"));
            openaiDalle2.setUrl(localUrl+downUrl);
            openaiDalleService.insertOpenaiDalle(openaiDalle2);
            //扣款次数
            openaiUserInfoService.updateOpenaiUserInfoByDallETime(userId,-1);
        }
        return AjaxResult.success();
    }

    /**
     * mj翻译和提示词
     * @param prompts
     * @param type
     * @param userId
     * @return
     */
    @Override
    public AjaxResult mjPrompts(String prompts, Long type, Long userId) {
        //todo 优化每个用户1分钟只能操作3次，
        if(prompts.length()<=0){
            return AjaxResult.error("请输入内容");
        }
        if(prompts.length()>500){
            return AjaxResult.error("字数不能大于500");
        }
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(userId);
        if(openaiUserInfo1.getMjMoneyTime()<=0){
            return AjaxResult.error("操作失败,请确保有绘画次数的时候再使用");
        }

        if (openaiUserInfo1.getFreeTime() <= 0) {
            if (openaiUserInfo1.getExpirationTime().before(new Date())) {
                if (openaiUserInfo1.getMoneyTime() <= 0) {
                    return AjaxResult.error("操作失败,ChatGPT账户余额不够");
                }
            }
        }


        ContextMessage contextMessage = new ContextMessage();
        contextMessage.setRole("system");
        if(type==1){
            contextMessage.setContent("我正在使用一个叫做Midjourney的AI图像生成工具。我想让你充当关键词生成器。我将在我想生成的主题之前添加\"/\"你会生成各种关键词。例如，如果我输入\"/跑车图像\"，你将生成关键词，如\"Realistic true details photography of Sports car,laction shots, speed motion blur, racing tracks, urban environments, scenic roads, dramatic skies\"。/");
        }else{
            contextMessage.setContent("你是一名英语翻译官，请你将下面的内容翻译成英文：");
        }
        List<ContextMessage> messages = new ArrayList<>();
        messages.add(contextMessage);
        ContextMessage contextMessage2 = new ContextMessage();
        contextMessage2.setRole("user");
        contextMessage2.setContent(prompts);
        messages.add(contextMessage2);
        ChatGPTResp chatGPTResp = socketAsync.synchronousChatGpt(openaiUserInfo1.getCompanyId(),messages);

        if (openaiUserInfo1.getFreeTime() <= 0) {
            if (openaiUserInfo1.getExpirationTime().before(new Date())) {
                if (openaiUserInfo1.getMoneyTime() <= 0) {
                    return AjaxResult.error("操作失败,ChatGPT账户余额不够");
                }else{
                    openaiUserInfoService.updateOpenaiUserInfoByMoneyTime(userId,-1);
                }
            }
        }else{
            openaiUserInfoService.updateOpenaiUserInfoByFreeTime(userId,-1);
        }

        return AjaxResult.success(chatGPTResp.getChoices().get(0).getMessage().getContent());
    }

    /**
     * AI识图上传图片
     * @param multipartFile
     * @param uuid
     * @param mId
     * @param userId
     * @return
     */
    @SneakyThrows
    @Override
    public AjaxResult uploadPicture(MultipartFile multipartFile, String uuid, Long mId, Long userId)  {

        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);
        if(openaiPrompts==null){
            SendEndMsg("模型错误!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }

        //判断用户的免费次数是否用完
        OpenaiUserInfo openaiUserInfo = new OpenaiUserInfo();
        openaiUserInfo.setUserId(userId);
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoList(openaiUserInfo).get(0);
        Long companyId = openaiUserInfo1.getCompanyId();
        String prompts = "图片上传失败，次数不够";
        //免费次数
        if (openaiUserInfo1.getMoneyTimeFour() <= 0) {

            SendEndMsg("客官,ChatGPT4.0 次数已用完! <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompts,uuid,mId,companyId);

            return AjaxResult.success();
        } else {

            String filePath = "";
//            try {
                filePath = FileUploadUtils.upload(RuoYiConfig.getAIPicturePath(), multipartFile, MimeTypeUtils.IMAGE_EXTENSION);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }

            OpenaiChat openaiChat2 = new OpenaiChat();
            openaiChat2.setUuid(uuid);
            openaiChat2.setStatus(0);
            openaiChat2.setMId(mId);
            List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat2);
            OpenaiChat openaiChat = new OpenaiChat();
            if(openaiChats.size()>0){
                openaiChat.setId(openaiChats.get(0).getId());
                openaiChat.setTitle("上传图片成功，请在输入框输入问题!例： 这个图片有什么？_____"+filePath);
                openaiChatService.updateOpenaiChat(openaiChat);
            }else{
                openaiChat.setTitle("上传图片成功，请在输入框输入问题!例： 这个图片有什么？_____"+filePath);
                openaiChat.setUuid(uuid);
                openaiChat.setUserId(userId);
                openaiChat.setMId(mId);
                openaiChat.setCompanyId(companyId);
                openaiChatService.insertOpenaiChat(openaiChat);
            }

            SendEndMsg("图片上传成功，请在输入框输入问题!例： 这个图片有什么？",userId.toString(),false,null,null,null,null);
        }

        return AjaxResult.success();
    }

    /**
     * AI识图 描述内容
     * @param prompt
     * @param uuid
     * @param mId
     * @param userId
     * @return
     */
    @Override
    public AjaxResult readPicture(String prompt, String uuid, Long mId, Long userId) {

        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);
        if(openaiPrompts==null){
            SendEndMsg("模型错误!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }

        //判断用户的免费次数是否用完
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(userId);
        Long companyId = openaiUserInfo1.getCompanyId();
        //免费次数
        if (openaiUserInfo1.getMoneyTimeFour() <= 0) {
            prompt = "图片上传失败，次数不够";
            SendEndMsg("客官,ChatGPT4.0 次数已用完! <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompt,uuid,mId,companyId);
            return AjaxResult.success();
        }

        OpenaiChat openaiChat2 = new OpenaiChat();
        openaiChat2.setUuid(uuid);
        openaiChat2.setStatus(0);
        openaiChat2.setMId(mId);
        List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat2);
        if(openaiChats.size()<1){
            SendEndMsg("请上传图片哟!",userId.toString(),false,null,null,null,null);
            return AjaxResult.success();
        }

        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts.getTId());
        ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener(socketIOService, openaiChatDetailService,openaiUserInfoService, userId.toString(), uuid,mId,openaiPromptsType.getType());

        String localUrl = configService.selectConfigByKeyAndCompanyId(openaiUserInfo1.getCompanyId(),"local_url").replace("/login","");
        String src = openaiChats.get(0).getTitle().split("_____")[1];
        String prompt2 = localUrl + src + " " +prompt;
        List<Message> messageList = new ArrayList<>();

        if(openaiPrompts.getModel().equals("gpt-4-vision-preview")){

            List<Map<String,Object>> list = new ArrayList<>();
            Map<String,Object> map = new HashMap<>();
            map.put("type","text");
            map.put("text",prompt);

            Map<String,Object> map2 = new HashMap<>();
            map2.put("type","image_url");
            map2.put("url",localUrl + src);

            list.add(map);
            list.add(map2);

            Message message = Message.builder().role(Message.Role.USER).content(list.toString()).build();
            messageList.add(message);
        }else{
            Message message = Message.builder().role(Message.Role.USER).content(prompt2).build();
            messageList.add(message);
        }

        //保存聊天记录
        if (openaiChats.size() == 1) {

            OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent("<img src="+localUrl+src+" style='width:50px;height:50px' />"+ " " +prompt);
            openaiChatDetail.setRole(Message.Role.USER.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(userId);
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

            ChatCompletion chatCompletion = ChatCompletion.builder().model(openaiPrompts.getModel()).messages(messageList).build();
            socketAsync.chatGpt4(openaiUserInfo1.getCompanyId(),eventSourceListener, chatCompletion);

        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult deleteMJTaskId(String taskId) {
        if(StringUtils.isEmpty(taskId)){
            return AjaxResult.error("taskId不能为空");
        }

        return AjaxResult.success();
    }


    /**
     * 发送消息
     * @param content 发送消息的内容
     * @param userId userId
     * @param temp true:把内容插入数据库 false:不插入
     * @param prompt prompt
     * @param uuid uuid
     * @param mId 模型id
     * @param companyId companyId
     */
    public void SendEndMsg(String content,String userId,boolean temp ,String prompt,String uuid,Long mId,Long companyId){

        PushMessage pushMessage = new PushMessage();
        pushMessage.setContent(content);
        pushMessage.setLoginUserNum(userId);
        socketIOService.pushMessageToUser(pushMessage);

        PushMessage pushMessage2 = new PushMessage();
        pushMessage2.setContent("[DONE]");
        pushMessage2.setLoginUserNum(userId);
        socketIOService.pushMessageToUser(pushMessage2);
        if(temp){

            OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent(prompt);
            openaiChatDetail.setRole(Message.Role.USER.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(Long.parseLong(userId));
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

            openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent(content);
            openaiChatDetail.setRole(Message.Role.ASSISTANT.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(Long.parseLong(userId));
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);
        }
    }
}

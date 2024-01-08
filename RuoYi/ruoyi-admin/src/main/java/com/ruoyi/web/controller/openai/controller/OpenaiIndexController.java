package com.ruoyi.web.controller.openai.controller;


import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.openai.domain.*;
import com.ruoyi.openai.service.*;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysNoticeService;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class OpenaiIndexController extends BaseController {


    @Autowired
    private IOpenaiIndexService openaiIndexService;
    @Autowired
    private IOpenaiChatDetailService openaiChatDetailService;
    @Autowired
    private IOpenaiPromptsService openaiPromptsService;
    @Autowired
    private IOpenaiChatPlanService openaiChatPlanService;
    @Autowired
    private IOpenaiUserInfoService openaiUserInfoService;
    @Autowired
    private FileChatService fileChatService;
    @Autowired
    private IOpenaiPromptsTypeService openaiPromptsTypeService;
    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    private ISysConfigService configService;
    @Autowired
    private IOpenaiChatOrderService openaiChatOrderService;
    @Autowired
    private IOpenaiDalleService openaiDalleService;
    @Autowired
    private IOpenaiChatService openaiChatService;

    /**
     * 获取菜单
     * @param title
     * @return
     */
    @PostMapping("/getMenu")
    @ResponseBody
    public AjaxResult getMenu(String title) {

        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }

        return openaiIndexService.getMenu(sysUser.getUserId(),title);
    }

    /**
     * 3.5模型聊天
     * @param prompt
     * @param uuid
     * @return
     */
    @PostMapping("/chat")
    @ResponseBody
    public AjaxResult chat(String prompt, String uuid,Long mId) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);

        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);
        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts.getTId());

        if(openaiPromptsType.getType()==1){
            // 3.5模型聊天
            return openaiIndexService.chat35(prompt,uuid,sysUser.getUserId(),mId);
        }else if(openaiPromptsType.getType()==2){
            //4.0模型聊天
            return openaiIndexService.chat4(prompt,uuid,sysUser.getUserId(),mId);
        }else if(openaiPromptsType.getType()==3){
            //pdf模型聊天
            return fileChatService.streamChatWithFile(prompt,uuid,mId,sysUser.getUserId());
        }else if(openaiPromptsType.getType()==5){
            //AI识图
            return openaiIndexService.readPicture(prompt,uuid,mId,sysUser.getUserId());
        }else if(openaiPromptsType.getType()==6){
            //4.0模型聊天
            return openaiIndexService.chat4(prompt,uuid,sysUser.getUserId(),mId);
        }else{
            //自定义角色模型
            return openaiIndexService.chatRole(prompt,uuid,mId,sysUser.getUserId());
        }
    }


    /**
     * 查询聊天记录详情列表
     */
    @PostMapping("/chatDetailList")
    @ResponseBody
    public TableDataInfo list(OpenaiChatDetail openaiChatDetail) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            throw new RuntimeException("服务器超时，请重新登录");
        }
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());
        openaiChatDetail.setUserId(sysUser.getUserId());
        openaiChatDetail.setStatus(0);
        openaiChatDetail.setCompanyId(openaiUserInfo1.getCompanyId());
        startPage();
        List<OpenaiChatDetail> list = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail);
        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(openaiChatDetail.getMId());
        if (list==null||list.size()==0){

            OpenaiPrompts openaiPrompts1 = openaiPromptsService.selectOpenaiPromptsById(openaiChatDetail.getMId());
            OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts1.getTId());

            list = new ArrayList<>();
            OpenaiChatDetail openaiChatDetail1 = new OpenaiChatDetail();
            if(openaiPromptsType.getType()==3){//AI文件阅读（PDF）
                String word = "<br/><div class=\"upload-container\"> <svg t=\"1693153944798\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"4946\" width=\"24\" height=\"24\"><path d=\"M804.23424 866.5344 630.5792 866.5344c-72.14592 0-129.62816-58.69568-129.62816-129.62816L500.95104 494.7712c0-9.78432 8.55552-18.34496 18.34496-18.34496 9.7792 0 18.34496 8.56064 18.34496 18.34496l0 240.91136c0 51.36896 41.57952 92.94336 92.93824 92.94336l173.65504 0c86.82496 0 158.98112-64.81408 163.87072-147.968 2.44736-44.02176-12.23168-86.82496-42.8032-118.62528-30.57152-31.80032-70.92736-50.14016-116.1728-50.14016l-3.67104 0c-6.11328 0-11.00288-2.44736-14.67392-6.11328-3.66592-4.8896-4.89472-9.7792-3.66592-15.89248 7.33696-35.46624 8.56064-72.15616 1.21856-107.6224-20.79232-118.62016-119.84384-209.11616-239.68768-222.5664-77.04576-8.56064-152.86272 15.8976-211.56352 68.48C279.6032 279.53664 245.36576 352.91136 245.36576 431.17568c0 8.56064-6.11328 17.11616-15.8976 18.34496-95.3856 13.45024-165.0944 97.83808-161.42336 195.66592 3.67104 100.2752 92.94336 183.43424 198.11328 183.43424l84.3776 0c9.78432 0 18.34496 8.55552 18.34496 18.33984 0 9.78944-8.56064 17.1264-19.56864 17.1264l-84.3776 0c-123.51488 0-228.6848-97.82784-233.5744-217.6768-4.89472-111.28832 70.92736-207.8976 177.32096-231.13728 4.8896-81.93536 41.57952-158.98112 103.94624-214.01088 66.03776-58.69568 151.63904-86.82496 240.91648-77.04064 135.74144 13.45024 248.24832 117.4016 271.48288 251.91936 6.11328 34.24256 7.33696 67.26144 2.44736 100.2752 47.6928 3.67104 92.93824 25.68704 124.73344 61.14304 37.90848 40.36096 56.25344 91.7248 52.58752 146.75456C998.67648 785.82272 910.62784 866.5344 804.23424 866.5344L804.23424 866.5344z\" fill=\"#2c2c2c\" p-id=\"4947\"></path><path d=\"M663.59808 631.73632c-4.8896 0-9.78432-1.22368-13.45536-4.8896l-132.0704-133.2992L385.9968 625.61792c-7.33696 7.34208-18.34496 7.34208-25.68192 0-7.33696-7.33696-7.33696-18.34496 0-25.68192l145.52576-145.52576c7.33696-7.33696 19.56352-7.33696 25.68192 0l145.53088 145.52576c7.33696 7.33696 7.33696 18.34496 0 25.68192C673.38752 629.29408 668.48768 631.73632 663.59808 631.73632L663.59808 631.73632z\" fill=\"#2c2c2c\" p-id=\"4948\"></path></svg> <input type=\"file\" onchange=\"uploadFile()\" id=\"myFile\" accept=\".pdf\"><div>上传pdf</div> </div>";
                openaiChatDetail1.setContent(openaiPrompts.getFirstContent()+word);
            }else if(openaiPromptsType.getType()==5){ //AI识图
                OpenaiChat openaiChat = new OpenaiChat();
                openaiChat.setUuid(openaiChatDetail.getUuid());
                openaiChat.setStatus(0);
                List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat);
                String word = "";
                if(openaiChats.size()==1){
                    word = "图片上传成功，请在输入框输入问题!例： 这个图片有什么？";
                    openaiChatDetail1.setContent(word);
                }else{
                    word = "<br/><div class=\"upload-container\"> <svg t=\"1693153944798\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"4946\" width=\"24\" height=\"24\"><path d=\"M804.23424 866.5344 630.5792 866.5344c-72.14592 0-129.62816-58.69568-129.62816-129.62816L500.95104 494.7712c0-9.78432 8.55552-18.34496 18.34496-18.34496 9.7792 0 18.34496 8.56064 18.34496 18.34496l0 240.91136c0 51.36896 41.57952 92.94336 92.93824 92.94336l173.65504 0c86.82496 0 158.98112-64.81408 163.87072-147.968 2.44736-44.02176-12.23168-86.82496-42.8032-118.62528-30.57152-31.80032-70.92736-50.14016-116.1728-50.14016l-3.67104 0c-6.11328 0-11.00288-2.44736-14.67392-6.11328-3.66592-4.8896-4.89472-9.7792-3.66592-15.89248 7.33696-35.46624 8.56064-72.15616 1.21856-107.6224-20.79232-118.62016-119.84384-209.11616-239.68768-222.5664-77.04576-8.56064-152.86272 15.8976-211.56352 68.48C279.6032 279.53664 245.36576 352.91136 245.36576 431.17568c0 8.56064-6.11328 17.11616-15.8976 18.34496-95.3856 13.45024-165.0944 97.83808-161.42336 195.66592 3.67104 100.2752 92.94336 183.43424 198.11328 183.43424l84.3776 0c9.78432 0 18.34496 8.55552 18.34496 18.33984 0 9.78944-8.56064 17.1264-19.56864 17.1264l-84.3776 0c-123.51488 0-228.6848-97.82784-233.5744-217.6768-4.89472-111.28832 70.92736-207.8976 177.32096-231.13728 4.8896-81.93536 41.57952-158.98112 103.94624-214.01088 66.03776-58.69568 151.63904-86.82496 240.91648-77.04064 135.74144 13.45024 248.24832 117.4016 271.48288 251.91936 6.11328 34.24256 7.33696 67.26144 2.44736 100.2752 47.6928 3.67104 92.93824 25.68704 124.73344 61.14304 37.90848 40.36096 56.25344 91.7248 52.58752 146.75456C998.67648 785.82272 910.62784 866.5344 804.23424 866.5344L804.23424 866.5344z\" fill=\"#2c2c2c\" p-id=\"4947\"></path><path d=\"M663.59808 631.73632c-4.8896 0-9.78432-1.22368-13.45536-4.8896l-132.0704-133.2992L385.9968 625.61792c-7.33696 7.34208-18.34496 7.34208-25.68192 0-7.33696-7.33696-7.33696-18.34496 0-25.68192l145.52576-145.52576c7.33696-7.33696 19.56352-7.33696 25.68192 0l145.53088 145.52576c7.33696 7.33696 7.33696 18.34496 0 25.68192C673.38752 629.29408 668.48768 631.73632 663.59808 631.73632L663.59808 631.73632z\" fill=\"#2c2c2c\" p-id=\"4948\"></path></svg> <input type=\"file\" onchange=\"uploadPicture()\" id=\"myPicture\" accept=\".jpg\"><div>上传图片</div> </div>";
                    openaiChatDetail1.setContent(openaiPrompts.getFirstContent()+word);
                }
            }else{
                openaiChatDetail1.setContent(openaiPrompts.getFirstContent());
            }
            openaiChatDetail1.setUuid(openaiChatDetail.getUuid());
            list.add(openaiChatDetail1);
        }else{
            PageDomain pageDomain = TableSupport.buildPageRequest();
            Integer pageNum = pageDomain.getPageNum();
            if(pageNum == 1){
                OpenaiChatDetail openaiChatDetail1 = new OpenaiChatDetail();
                openaiChatDetail1.setContent(openaiPrompts.getFirstContent());
                openaiChatDetail1.setUuid(openaiChatDetail.getUuid());
                openaiChatDetail1.setRole(Message.Role.ASSISTANT.getName());
                list.add(openaiChatDetail1);
                if(list.size() == 2){
                    Collections.reverse(list);
                    OpenaiChatDetail openaiChatDetail2 = new OpenaiChatDetail();
                    openaiChatDetail2.setContent("");
                    openaiChatDetail2.setUuid(openaiChatDetail.getUuid());
                    openaiChatDetail2.setRole(Message.Role.ASSISTANT.getName());
                    list.add(openaiChatDetail2);
                    Collections.reverse(list);
                }
            }
        }
        for (OpenaiChatDetail chatDetail : list) {
            chatDetail.setId(null);
            chatDetail.setCompanyId(null);
        }
        return getDataTable(list);
    }


    /**
     * 获取价格列表
     */
    @PostMapping("/priceList")
    @ResponseBody
    public TableDataInfo list() {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            throw new RuntimeException("服务器超时，请重新登录");
        }
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());
        OpenaiChatPlan openaiChatPlan = new OpenaiChatPlan();
        openaiChatPlan.setStatus(0);
        openaiChatPlan.setCompanyId(openaiUserInfo1.getCompanyId());
        String orderBy = "sort desc";
        PageHelper.orderBy(orderBy);
        List<OpenaiChatPlan> list = openaiChatPlanService.selectOpenaiChatPlanList(openaiChatPlan);

        String experienced = configService.selectConfigByKeyAndCompanyId(openaiUserInfo1.getCompanyId(),"experienced");
        for (OpenaiChatPlan chatPlan : list) {
            //开启
            if(experienced.equals("0")){
                if(chatPlan.getExperienced()==0){
                    //判断当前用户有没有首充
                    List<OpenaiChatOrder> openaiChatOrders = openaiChatOrderService.selectOpenaiChatOrderListByUserId(openaiUserInfo1.getUserId());
                    if(openaiChatOrders.size()>0){
                        chatPlan.setExperiencedStr(false);
                    }else{
                        chatPlan.setExperiencedStr(true);
                    }
                }else{
                    chatPlan.setExperiencedStr(true);
                }
            }else{
                chatPlan.setExperiencedStr(true);
            }
        }

        return getDataTable(list);
    }


    /**
     * 获取我的推广信息
     */
    @PostMapping("/getMyPromoteInfo")
    @ResponseBody
    public AjaxResult getMyPromoteInfo() {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        return openaiUserInfoService.getMyPromoteInfo(sysUser.getUserId());
    }

    /**
     * 提现
     */
    @PostMapping("/getMoney")
    @ResponseBody
    public AjaxResult getMoney() {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        return openaiUserInfoService.getMoney(sysUser.getUserId());
    }


    /**
     * 删除聊天记录&详情
     */
    @PostMapping("/deleteChatList")
    @ResponseBody
    public AjaxResult deleteChatList(OpenaiChatDetail openaiChatDetail) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        return openaiIndexService.deleteChatList(openaiChatDetail,sysUser.getUserId());
    }


    /**
     * AI 文件上传
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile multipartFile,String uuid,Long mId) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        return fileChatService.uploadFile(multipartFile,uuid,mId,sysUser.getUserId());
    }


    /**
     * 获取通知
     * @param type 1: 查看公告
     * @return
     */
    @PostMapping("/getNotice")
    @ResponseBody
    public AjaxResult getNotice(String type) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        OpenaiUserInfo openaiUserInfo = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());
        if(openaiUserInfo.getRead()==0 || StringUtils.isNotEmpty(type)){
            SysNotice notice = new SysNotice();
            notice.setCompanyId(openaiUserInfo.getCompanyId());
            notice.setStatus("0");
            PageHelper.orderBy("notice_id desc");
            PageHelper.startPage(1, 1 );
            List<SysNotice> sysNotices = noticeService.selectNoticeList(notice);
            return AjaxResult.success(sysNotices.size()==0?null:sysNotices.get(0));
        }else{
            return AjaxResult.success(null);
        }
    }


    /**
     *  获取通知
     */
    @PostMapping("/okayRead")
    @ResponseBody
    public AjaxResult okayRead() {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        OpenaiUserInfo openaiUserInfo = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());
        openaiUserInfo.setRead(1);
        openaiUserInfoService.updateOpenaiUserInfo(openaiUserInfo);
        return AjaxResult.success();
    }


    /**
     * DALL.E 3 绘画
     * @return
     */
    @PostMapping("/dallE3List")
    @ResponseBody
    public TableDataInfo dallE3List(OpenaiDalle openaiDalle) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            throw new RuntimeException("服务器超时，请重新登录");
        }
        openaiDalle.setUserId(sysUser.getUserId());
        openaiDalle.setStatus(0);
        startPage();
        String orderBy = "create_time desc";
        PageHelper.orderBy(orderBy);
        List<OpenaiDalle> openaiDalles = openaiDalleService.selectOpenaiDalleList(openaiDalle);
        for (OpenaiDalle dalle : openaiDalles) {
            dalle.setId(null);
            dalle.setCompanyId(null);
        }
        return getDataTable(openaiDalles);
    }

    /**
     * DALL.E 3 绘画
     * @return
     */
    @PostMapping("/dallE3")
    @ResponseBody
    public AjaxResult dallE3(OpenaiDalle openaiDalle) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        return openaiIndexService.dallE3(openaiDalle,sysUser.getUserId());
    }


    /**
     * AI识图上传图片
     */
    @PostMapping("/uploadPicture")
    @ResponseBody
    public AjaxResult uploadPicture(MultipartFile multipartFile,String uuid,Long mId) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        return openaiIndexService.uploadPicture(multipartFile,uuid,mId,sysUser.getUserId());
    }

    /**
     * 获取我的具体次数信息
     * @return
     */
    @PostMapping("/getInfo")
    @ResponseBody
    public AjaxResult getInfo() {

        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        Map<String,Object> mmap = new HashMap<>();
        OpenaiUserInfo openaiUserInfo = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());
        mmap.put("freeTime", openaiUserInfo.getFreeTime());
        mmap.put("moneyTime", openaiUserInfo.getMoneyTime());
        mmap.put("moneyTimeFour", openaiUserInfo.getMoneyTimeFour());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mmap.put("rechargeTime", simpleDateFormat.format(openaiUserInfo.getRechargeTime()));
        mmap.put("expirationTime", simpleDateFormat.format(openaiUserInfo.getExpirationTime()));
        mmap.put("rechargeTimeFour", simpleDateFormat.format(openaiUserInfo.getRechargeTimeFour()));
        mmap.put("expirationTimeFour", simpleDateFormat.format(openaiUserInfo.getExpirationTimeFour()));
        mmap.put("mjFreeTime", openaiUserInfo.getMjFreeTime());
        mmap.put("mjMoneyTime", openaiUserInfo.getMjMoneyTime());
        mmap.put("dallETime", openaiUserInfo.getDallETime());

        return AjaxResult.success(mmap);
    }
}

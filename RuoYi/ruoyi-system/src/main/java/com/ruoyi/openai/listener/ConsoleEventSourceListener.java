package com.ruoyi.openai.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.openai.domain.OpenaiChatDetail;
import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.domain.PushMessage;
import com.ruoyi.openai.service.IOpenaiChatDetailService;
import com.ruoyi.openai.service.IOpenaiUserInfoService;
import com.ruoyi.openai.service.SocketIOService;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;


@RequiredArgsConstructor
public class ConsoleEventSourceListener extends EventSourceListener {
    private static final Logger log = LoggerFactory.getLogger(com.unfbx.chatgpt.sse.ConsoleEventSourceListener.class);

    final SocketIOService socketIOService;
    final IOpenaiChatDetailService openaiChatDetailService;
    final IOpenaiUserInfoService openaiUserInfoService;

    final String userId;
    final String uuid;
    final Long mId;
    final Long mType;


    String last = "";

    public void onOpen(EventSource eventSource, Response response) {

    }

    @SneakyThrows
    public void onEvent(EventSource eventSource, String id, String type, String data) {

        if (data.equals("[DONE]")) {
            OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
            if(mType==3){//PDF的回答
                openaiChatDetail.setContent2(last);
            }
            OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(Long.valueOf(userId));
            openaiChatDetail.setCompanyId(openaiUserInfo1.getCompanyId());
            openaiChatDetail.setContent(last);
            openaiChatDetail.setRole(Message.Role.ASSISTANT.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(Long.parseLong(userId));
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setType(mType);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

            //扣次数
            subtractAccount(mType,userId);

        }else{

            ChatCompletionResponse completionResponse = JSON.parseObject(data,
                    ChatCompletionResponse.class); // 读取Json
            Message delta = completionResponse.getChoices().get(0).getDelta();
            String text = delta.getContent();

            if (text != null) {
                last += text;
                try {

                    PushMessage pushMessage = new PushMessage();
                    pushMessage.setContent(text);
                    pushMessage.setLoginUserNum(userId);
                    socketIOService.pushMessageToUser(pushMessage);

                } catch (Exception ex) {
                    // 客户端已经断开连接，这里你可以选择记录日志或进行其他操作
                    log.info("客户端已经断开连接，这里你可以选择记录日志或进行其他操作");
                    log.error("Client has closed the connection", ex);
                }
            }
        }
    }

    public void onClosed(EventSource eventSource) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.setContent("[DONE]");
        pushMessage.setLoginUserNum(userId);
        socketIOService.pushMessageToUser(pushMessage);
    }

    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        try {
            if (Objects.isNull(response)) {
                log.error("OpenAI  sse连接异常:{}", t);
                SendEndMsg(t.toString() + "系统异常，请重新发送或请联系管理员!!!",userId);
            } else {
                ResponseBody body = response.body();
                if (Objects.nonNull(body)) {

                    String string = body.string();
                    log.error("OpenAI 返回异常:{}", string);
                    JSONObject jsonObject = JSONObject.parseObject(string);
                    JSONObject jsonObject2 = (JSONObject)jsonObject.get("error");
                    String message = jsonObject2.getString("message");
                    if(message.contains("This model's maximum context length is 16385 tokens")){
                        SendEndMsg(string + "请控制内容长度!!!",userId);
                    }else if(message.equals("Your account is not active, please check your billing details on our website.")){
                        SendEndMsg(string + "请联系管理员处理账单!!!",userId);
                    }else{
                        SendEndMsg(string + "系统异常，请重新发送或请联系管理员!!!",userId);
                    }

                    log.error("OpenAI  sse连接异常data：{}，异常：{}", string, t);
                } else {
                    log.error("OpenAI  sse连接异常data：{}，异常：{}", response, t);
                    SendEndMsg("程序开了个小差，请联系管理员",userId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            SendEndMsg("网络异常,请稍后再试!",userId);
        }
    }

    /**
     * 发送消息
     * @param content
     * @param userId
     */
    public void SendEndMsg(String content,String userId){

        PushMessage pushMessage = new PushMessage();
        pushMessage.setContent(content);
        pushMessage.setLoginUserNum(userId);
        socketIOService.pushMessageToUser(pushMessage);

        PushMessage pushMessage2 = new PushMessage();
        pushMessage2.setContent("[DONE]");
        pushMessage2.setLoginUserNum(userId);
        socketIOService.pushMessageToUser(pushMessage2);
    }

    /**
     * 消息发送完了就减用户剩余次数
     * @param mType 模型类型
     * @param userId
     */
    public void subtractAccount(Long mType,String userId){
        Long userId2 = Long.parseLong(userId);
        //判断用户的免费次数是否用完
        OpenaiUserInfo openaiUserInfo = new OpenaiUserInfo();
        openaiUserInfo.setUserId(userId2);
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoList(openaiUserInfo).get(0);

        if(mType==2 || mType==3 || mType==5){
            if (openaiUserInfo1.getMoneyTimeFour() > 0) {
                openaiUserInfoService.updateOpenaiUserInfoByMoneyTimeFour(userId2,-1);
            }
        }else{
            if (openaiUserInfo1.getFreeTime() <= 0) {
                if (openaiUserInfo1.getExpirationTime() != null && openaiUserInfo1.getRechargeTime() != null) {
                    if (openaiUserInfo1.getExpirationTime().before(new Date())) {
                        if (openaiUserInfo1.getMoneyTime() > 0) {
                            openaiUserInfoService.updateOpenaiUserInfoByMoneyTime(userId2,-1);
                        }
                    }
                }
            } else {
                openaiUserInfoService.updateOpenaiUserInfoByFreeTime(userId2,-1);
            }
        }
    }

}

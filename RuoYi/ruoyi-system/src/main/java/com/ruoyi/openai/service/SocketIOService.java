package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.PushMessage;

import java.util.List;

/**
 * 功能描述
 *
 * @author: zyu
 * @description:
 * @date: 2019/4/23 10:41
 */
public interface SocketIOService {

    //推送的事件
    public static final String PUSH_EVENT = "push_event";

    // 启动服务
    public void start() throws Exception;

    // 停止服务
    public void stop();

    // 推送信息
    public void pushMessageToUser(PushMessage pushMessage);

    public Integer getPerPerCount();

    public List<String> perpeoName();
}
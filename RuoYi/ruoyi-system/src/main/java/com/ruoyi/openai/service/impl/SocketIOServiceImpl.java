package com.ruoyi.openai.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.ruoyi.openai.domain.PushMessage;
import com.ruoyi.openai.service.SocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.ruoyi.openai.service.SocketIOService.PUSH_EVENT;

/**
 * 功能描述
 *
 * @author: zyu
 * @description:
 * @date: 2019/4/23 10:42
 */
@Service(value = "socketIOService")
public class SocketIOServiceImpl implements SocketIOService {

    // 用来存已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
     *
     * @throws Exception
     */
    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     *
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    @Override
    public void start() throws Exception {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                System.out.println(loginUserNum);
                System.out.println("SessionId:  " + client.getSessionId());
                System.out.println("RemoteAddress:  " + client.getRemoteAddress());
                System.out.println("Transport:  " + client.getTransport());
                clientMap.put(loginUserNum, client);
                System.out.println("目前总共有："+clientMap.size()+"人");
            }
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                clientMap.remove(loginUserNum);
                System.out.println("目前总共有："+clientMap.size()+"人");
                System.out.println("断开连接： " + loginUserNum);
                System.out.println("断开连接： " + client.getSessionId());
                client.disconnect();
            }
        });

        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener("text", Object.class, (client, data, ackSender) -> {
            client.getHandshakeData();
            System.out.println( " 客户端：************ " + data);
            JSONObject parse = (JSONObject) JSONObject.parse(data.toString());
            String type = (String) parse.get("type");//类型： 1：全部  2：某个人 3：等等...
            if("1".equals(type)){
                Collection<SocketIOClient> values = clientMap.values();
                Iterator<SocketIOClient> iterator = values.iterator();
                while (iterator.hasNext()){
                    SocketIOClient clients = iterator.next();
                    if (clients != null) {
                        PushMessage pushMessage = new PushMessage();
                        pushMessage.setContent(parse.getString("content"));
                        clients.sendEvent(PUSH_EVENT, pushMessage);
                    }
                }
            }else if("2".equals(type)){
                PushMessage pushMessage = new PushMessage();
                pushMessage.setContent(parse.getString("content"));
                pushMessage.setLoginUserNum(parse.getString("toUserId"));
                pushMessageToUser(pushMessage);
            }

        });

        socketIOServer.start();
    }



    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushMessageToUser(PushMessage pushMessage) {
        String loginUserNum = pushMessage.getLoginUserNum();
        if (loginUserNum!=null) {
            SocketIOClient client = clientMap.get(loginUserNum);
            if (client != null)
                client.sendEvent(PUSH_EVENT, pushMessage);
        }
    }

    @Override
    public Integer getPerPerCount() {
        return clientMap.size();
    }

    @Override
    public List<String> perpeoName() {
        List<String> list = new ArrayList<>();
        // 遍历键值对
        for (Map.Entry<String, SocketIOClient> entry : clientMap.entrySet()) {
            String key = entry.getKey(); // 获取键
            SocketIOClient value = entry.getValue(); // 获取值
            list.add("Key: " + key + ", Value: " + value+"<br />");
        }
        return list;
    }

    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     * @param client
     * @return
     */
    private String getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数（这里的loginUserNum必须是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("loginUserNum");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static Map<String, SocketIOClient> getClientMap() {
        return clientMap;
    }

    public static void setClientMap(Map<String, SocketIOClient> clientMap) {
        SocketIOServiceImpl.clientMap = clientMap;
    }
}
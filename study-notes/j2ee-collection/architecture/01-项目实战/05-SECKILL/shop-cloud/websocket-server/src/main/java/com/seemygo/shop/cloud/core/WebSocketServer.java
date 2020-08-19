package com.seemygo.shop.cloud.core;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * WebSocket 服务连接实例
 * ws://localhost:8095/111111
 */
@ServerEndpoint("/{uuid}")
@Component
public class WebSocketServer {
    /**
     * 当有客户端建立连接时, 进入此方法
     *
     * @param uuid
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("uuid") String uuid, Session session) {
        System.out.println("客户端: " + uuid + "连接上来了");
        WebSocketSessionManager.INSTANCE.put(uuid, session);
    }

    @OnMessage
    public void onMessage(@PathParam("uuid") String uuid, String message) {
        System.out.println("收到客户端: " + uuid + "的消息: " + message);
    }

    @OnClose
    public void onClose(@PathParam("uuid") String uuid) {
        System.out.println("客户端: " + uuid + "关闭连接了");
        if (!StringUtils.isEmpty(uuid)) {
            WebSocketSessionManager.INSTANCE.remove(uuid);
        }
    }

    @OnError
    public void onError(@PathParam("uuid") String uuid, Throwable throwable) {
        System.out.println("客户端: " + uuid + "出现异常了");
        throwable.printStackTrace();
    }
}

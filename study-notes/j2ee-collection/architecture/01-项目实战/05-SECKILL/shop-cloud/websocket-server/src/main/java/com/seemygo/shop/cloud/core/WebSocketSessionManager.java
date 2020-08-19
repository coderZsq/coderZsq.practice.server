package com.seemygo.shop.cloud.core;

import com.seemygo.shop.cloud.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public enum WebSocketSessionManager {
    INSTANCE;

    private final ConcurrentHashMap<String, Session> CLIENT_SESSION_MAP = new ConcurrentHashMap<>();

    public boolean put(String uuid, Session session) {
        return CLIENT_SESSION_MAP.put(uuid, session) != null;
    }

    public boolean remove(String uuid) {
        return CLIENT_SESSION_MAP.remove(uuid) != null;
    }

    public boolean sendMsg(String uuid, Object msg) {
        Session session = CLIENT_SESSION_MAP.get(uuid);

        if (session == null) {
            log.warn("[session manager] 发送消息失败 uuid={}", uuid);
            return false;
        }

        try {
            // 发送消息
            String result = JSONUtil.toJSONString(msg);
            session.getBasicRemote().sendText(result);
            log.info("[session manager] 发送消息到客户端成功: {}", result);
        } catch (Exception e) {
            log.error("[session manager] 发送消息到客户端失败", e);
            return false;
        }

        return true;
    }
}

package cn.wolfcode.netty.im.server.connertor.impl;

import cn.wolfcode.netty.im.constant.Constants;
import cn.wolfcode.netty.im.server.connertor.ImConnector;
import cn.wolfcode.netty.im.server.exception.PushException;
import cn.wolfcode.netty.im.server.group.ImChannelGroup;
import cn.wolfcode.netty.im.server.model.MessageWrapper;
import cn.wolfcode.netty.im.server.model.Session;
import cn.wolfcode.netty.im.server.proxy.MessageProxy;
import cn.wolfcode.netty.im.server.session.SessionManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Leon
 */
public class ImConnectorImpl implements ImConnector {
    private final static Logger log = LoggerFactory.getLogger(ImConnectorImpl.class);

    private SessionManager sessionManager;
    private MessageProxy proxy;

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setProxy(MessageProxy proxy) {
        this.proxy = proxy;
    }


    @Override
    public void heartBeatToClient(ChannelHandlerContext handler, MessageWrapper wrapper) {
        //设置心跳响应时间
        handler.channel().attr(Constants.SessionConfig.SERVER_SESSION_HEARTBEAT).set(System.currentTimeMillis());
    }

    @Override
    public void pushGroupMessage(MessageWrapper wrapper)
            throws RuntimeException {
        //这里判断群组ID 是否存在 并且该用户是否在群组内
        ImChannelGroup.broadcast(wrapper.getBody());
        proxy.saveOnlineMessageToDB(wrapper);
    }

    @Override
    public void pushMessage(MessageWrapper wrapper) throws RuntimeException {
        try {
            //sessionManager.send(wrapper.getSessionId(), wrapper.getBody());
            Session session = sessionManager.getSession(wrapper.getSessionId());
            /*
             * 服务器集群时，可以在此
             * 判断当前session是否连接于本台服务器，如果是，继续往下走，如果不是，将此消息发往当前session连接的服务器并 return
             * if(session!=null&&!session.isLocalhost()){//判断当前session是否连接于本台服务器，如不是
             * //发往目标服务器处理
             * return;
             * }
             */
            if (session != null) {
                boolean result = session.write(wrapper.getBody());
                return;
            }
        } catch (Exception e) {
            log.error("connector pushMessage  Exception.", e);
            throw new RuntimeException(e.getCause());
        }
    }


    @Override
    public void pushMessage(String sessionId, MessageWrapper wrapper) throws RuntimeException {
        // 判断是不是无效用户回复
        Session session = sessionManager.getSession(sessionId);
        if (session == null) {
            throw new RuntimeException(String.format("session %s is not exist.", sessionId));
        }
        try {
            ///取得接收人 给接收人写入消息
            Session responseSession = sessionManager.getSession(wrapper.getReSessionId());
            if (responseSession != null && responseSession.isConnected()) {
                boolean result = responseSession.write(wrapper.getBody());
                if (result) {
                    proxy.saveOnlineMessageToDB(wrapper);
                } else {
                    proxy.saveOfflineMessageToDB(wrapper);
                }
                return;
            } else {
                proxy.saveOfflineMessageToDB(wrapper);
            }
        } catch (PushException e) {
            log.error("connector send occur PushException.", e);

            throw new RuntimeException(e.getCause());
        } catch (Exception e) {
            log.error("connector send occur Exception.", e);
            throw new RuntimeException(e.getCause());
        }

    }

    @Override
    public boolean validateSession(MessageWrapper wrapper) throws RuntimeException {
        try {
            return sessionManager.exist(wrapper.getSessionId());
        } catch (Exception e) {
            log.error("connector validateSession Exception!", e);
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public void close(ChannelHandlerContext hander, MessageWrapper wrapper) {
        String sessionId = getChannelSessionId(hander);
        if (StringUtils.isNotBlank(sessionId)) {
            close(hander);
            log.warn("connector close channel sessionId -> " + sessionId + ", ctx -> " + hander.toString());
        }
    }

    @Override
    public void close(ChannelHandlerContext handler) {
        String sessionId = getChannelSessionId(handler);
        try {
            if (sessionId == null) {
                handler.close();
                log.warn("closing channel[" + handler.channel() + "] but sessionId is -> " + sessionId);
                return;
            }

            String nid = handler.channel().id().asShortText();
            System.out.println("准备关闭session：" + sessionId);
            Session session = sessionManager.getSession(sessionId);
            if (session != null) {
                sessionManager.removeSession(sessionId, nid);
                ImChannelGroup.remove(handler.channel());
                log.info("connector close sessionId -> " + sessionId + " success ");
            }
        } catch (Exception e) {
            log.error("connector close sessionId -->" + sessionId + "  Exception.", e);
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public void close(String sessionId) {
        try {
            Session session = sessionManager.getSession(sessionId);
            if (session != null) {
                sessionManager.removeSession(sessionId);
                List<Channel> list = session.getSessionAll();
                for (Channel ch : list) {
                    ImChannelGroup.remove(ch);
                }
                log.info("connector close sessionId -> " + sessionId + " success ");
            }
        } catch (Exception e) {
            log.error("connector close sessionId -->" + sessionId + "  Exception.", e);
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public void connect(ChannelHandlerContext ctx, MessageWrapper wrapper) {
        try {
            String sessionId = wrapper.getSessionId();
            String sessionId0 = getChannelSessionId(ctx);
            //当 sessionID 存在或者相等  视为同一用户重新连接
            if (StringUtils.isNotEmpty(sessionId0) || sessionId.equals(sessionId0)) {
                log.info("connector reconnect sessionId -> " + sessionId + ", ctx -> " + ctx.toString());
                pushMessage(proxy.getReConnectionStateMsg(sessionId0));
            } else {
                log.info("connector connect sessionId -> " + sessionId + ", sessionId0 -> " + sessionId0 + ", ctx -> " + ctx.toString());
                sessionManager.createSession(wrapper, ctx);
                setChannelSessionId(ctx, sessionId);
                log.info("create channel attr sessionId " + sessionId + " successful, ctx -> " + ctx.toString());
            }
        } catch (Exception e) {
            log.error("connector connect  Exception.", e);
        }
    }

    @Override
    public boolean exist(String sessionId) throws Exception {
        return sessionManager.exist(sessionId);
    }

    @Override
    public String getChannelSessionId(ChannelHandlerContext ctx) {
        return ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_ID).get();
    }

    private void setChannelSessionId(ChannelHandlerContext ctx, String sessionId) {
        ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_ID).set(sessionId);
    }


}

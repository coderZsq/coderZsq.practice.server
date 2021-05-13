package cn.wolfcode.netty.im.server.handler;

import cn.wolfcode.netty.im.constant.Constants;
import cn.wolfcode.netty.im.server.connertor.ImConnector;
import cn.wolfcode.netty.im.server.model.MessageProto;
import cn.wolfcode.netty.im.server.model.MessageWrapper;
import cn.wolfcode.netty.im.server.proxy.MessageProxy;
import cn.wolfcode.netty.im.util.ImUtils;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Leon
 */
@Sharable
public class ImWebSocketServerHandler extends SimpleChannelInboundHandler<MessageProto.Model> {

    private final static Logger log = LoggerFactory.getLogger(ImWebSocketServerHandler.class);
    private ImConnector connector = null;
    private MessageProxy proxy = null;

    public ImWebSocketServerHandler(MessageProxy proxy, ImConnector connector) {
        this.connector = connector;
        this.proxy = proxy;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object o) throws Exception {
        String sessionId = ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_ID).get();
        // 发送心跳包
        if (o instanceof IdleStateEvent && ((IdleStateEvent) o).state().equals(IdleState.WRITER_IDLE)) {
            if (StringUtils.isNotEmpty(sessionId)) {
                MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
                builder.setCmd(Constants.CmdType.HEARTBEAT);
                builder.setMsgType(Constants.ProtoBufType.SEND);
                ctx.channel().writeAndFlush(builder);
            }
            log.debug(IdleState.WRITER_IDLE + "... from " + sessionId + "-->" + ctx.channel().remoteAddress() + " nid:" + ctx.channel().id().asShortText());
        }

        // 如果心跳请求发出 70 秒内没收到响应，则关闭连接
        if (o instanceof IdleStateEvent && ((IdleStateEvent) o).state().equals(IdleState.READER_IDLE)) {
            log.debug(IdleState.READER_IDLE + "... from " + sessionId + " nid:" + ctx.channel().id().asShortText());
            Long lastTime = (Long) ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_HEARTBEAT).get();
            if (lastTime == null || ((System.currentTimeMillis() - lastTime) / 1000 >= Constants.ImServerConfig.PING_TIME_OUT)) {
                connector.close(ctx);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProto.Model message)
            throws Exception {
        try {
            String sessionId = connector.getChannelSessionId(ctx);
            // inbound
            if (message.getMsgType() == Constants.ProtoBufType.SEND) {
                ctx.channel().attr(Constants.SessionConfig.SERVER_SESSION_HEARTBEAT).set(System.currentTimeMillis());
                MessageWrapper wrapper = proxy.convertToMessageWrapper(sessionId, message);
                if (wrapper != null) {
                    receiveMessages(ctx, wrapper);
                }
            }
            // outbound
            if (message.getMsgType() == Constants.ProtoBufType.REPLY) {
                MessageWrapper wrapper = proxy.convertToMessageWrapper(sessionId, message);
                if (wrapper != null) {
                    receiveMessages(ctx, wrapper);
                }
            }
        } catch (Exception e) {
            log.error("ImWebSocketServerHandler channelRead error.", e);
            throw e;
        }

    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("ImWebSocketServerHandler join from " + ImUtils.getRemoteAddress(ctx) + " nid:" + ctx.channel().id().asShortText());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.debug("ImWebSocketServerHandler Disconnected from {" + ctx.channel().remoteAddress() + "--->" + ctx.channel().localAddress() + "}");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.debug("ImWebSocketServerHandler channelActive from (" + ImUtils.getRemoteAddress(ctx) + ")");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.debug("ImWebSocketServerHandler channelInactive from (" + ImUtils.getRemoteAddress(ctx) + ")");
        String sessionId = connector.getChannelSessionId(ctx);
        receiveMessages(ctx, new MessageWrapper(MessageWrapper.MessageProtocol.CLOSE, sessionId, null, null));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("ImWebSocketServerHandler (" + ImUtils.getRemoteAddress(ctx) + ") -> Unexpected exception from downstream." + cause);
    }


    /**
     * to send message
     *
     * @param handler
     * @param wrapper
     */
    private void receiveMessages(ChannelHandlerContext handler, MessageWrapper wrapper) {
        // 设置消息来源为 Websocket
        wrapper.setSource(Constants.ImServerConfig.WEBSOCKET);
        if (wrapper.isConnect()) {
            connector.connect(handler, wrapper);
        } else if (wrapper.isClose()) {
            connector.close(handler, wrapper);
        } else if (wrapper.isHeartbeat()) {
            connector.heartBeatToClient(handler, wrapper);
        } else if (wrapper.isGroup()) {
            connector.pushGroupMessage(wrapper);
        } else if (wrapper.isSend()) {
            connector.pushMessage(wrapper);
        } else if (wrapper.isReply()) {
            connector.pushMessage(wrapper.getSessionId(), wrapper);
        }
    }
}

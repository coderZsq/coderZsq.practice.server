package cn.wolfcode.netty.im.server.connertor;

import cn.wolfcode.netty.im.server.model.MessageWrapper;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Leon
 */
public interface ImConnector {
    /**
     * 发送心跳检测 到客户端
     *
     * @param handler
     * @param wrapper
     */
    void heartBeatToClient(ChannelHandlerContext handler, MessageWrapper wrapper);

    /**
     * 发送消息
     *
     * @param wrapper
     * @throws RuntimeException
     */
    void pushMessage(MessageWrapper wrapper) throws RuntimeException;

    /**
     * 发送组消息
     *
     * @param wrapper
     * @throws RuntimeException
     */
    void pushGroupMessage(MessageWrapper wrapper) throws RuntimeException;

    /**
     * 验证session
     *
     * @param wrapper
     * @return
     * @throws RuntimeException
     */
    boolean validateSession(MessageWrapper wrapper) throws RuntimeException;

    /**
     * 关闭连接
     *
     * @param handler
     * @param wrapper
     */
    void close(ChannelHandlerContext handler, MessageWrapper wrapper);

    /**
     * 关闭连接
     *
     * @param sessionId
     */
    void close(String sessionId);

    /**
     * 关闭连接
     *
     * @param handler
     */
    void close(ChannelHandlerContext handler);

    /**
     * 建立连接
     *
     * @param ctx
     * @param wrapper
     */
    void connect(ChannelHandlerContext ctx, MessageWrapper wrapper);

    /**
     * 退出 session
     *
     * @param sessionId
     * @return
     * @throws Exception
     */
    boolean exist(String sessionId) throws Exception;

    /**
     * 发送消息
     *
     * @param sessionId 发送人
     * @param wrapper   发送内容
     * @throws RuntimeException
     */
    void pushMessage(String sessionId, MessageWrapper wrapper) throws RuntimeException;

    /**
     * 获取用户唯一标识符
     *
     * @param ctx
     * @return
     */
    String getChannelSessionId(ChannelHandlerContext ctx);
}

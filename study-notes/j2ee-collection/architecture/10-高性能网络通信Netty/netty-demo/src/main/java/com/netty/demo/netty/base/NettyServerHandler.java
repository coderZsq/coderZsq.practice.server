package cn.wolfcode.netty.demo.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义 Handler 需要继承 netty 规定好的某个 HandlerAdapter (规范)
 *
 * @author Leon
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接成功时会调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接成功...");
    }

    /**
     * 当客户端发送数据到服务端时，通过该方法接收
     *
     * @param ctx 上下文对象，含有通道 channel，管道 pipeline
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Channel channel = ctx.channel();
        // 本质是一个双向链接，出站入站
        // ChannelPipeline pipeline = ctx.pipeline();
        // 将 msg 转成一个 ByteBuf，类似 NIO 的 ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("收到客户端消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 当数据读取完成后调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // 向客户端回传数据
        ByteBuf buf = Unpooled.copiedBuffer("<SERVER>: hello client...".getBytes(CharsetUtil.UTF_8));
        // 写出并刷新
        ctx.writeAndFlush(buf);
    }

    /**
     * 处理异常，一般是需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}

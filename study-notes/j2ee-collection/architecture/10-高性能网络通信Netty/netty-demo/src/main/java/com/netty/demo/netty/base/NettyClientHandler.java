package cn.wolfcode.netty.demo.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Leon
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道激活：当客户端连接服务端成功时会调用该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 连接成功后向服务端发送数据
        ByteBuf buf = Unpooled.copiedBuffer("<CLIENT>: hello...".getBytes(CharsetUtil.UTF_8));
        // 写出数据并刷新
        ctx.writeAndFlush(buf);
    }

    /**
     * 当通道有读取事件时：服务端回传数据给客户端时，再此接收消息
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("收到服务端回传消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 当通道出现异常时执行
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

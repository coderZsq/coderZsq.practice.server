package cn.wolfcode.netty.demo.netty.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author Leon
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<NettyMessageProtocol> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 20; i++) {
            String msg = "你好，我是张三！";

            // 创建协议包对象
            NettyMessageProtocol messageProtocol = new NettyMessageProtocol();
            byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
            messageProtocol.setLen(bytes.length);
            messageProtocol.setContent(bytes);

            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessageProtocol msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}

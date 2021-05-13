package cn.wolfcode.netty.demo.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Leon
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("从客户端读取到String：" + msg.toString() + ", 获取到的内容：" + msg);
//        System.out.println("从客户端读取到Object：" + ((User) msg).toString());
        // 测试用 protostuff 对对象编解码
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println("从客户端读取到Object：" + ProtoStuffUtil.deserializer(bytes, User.class));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

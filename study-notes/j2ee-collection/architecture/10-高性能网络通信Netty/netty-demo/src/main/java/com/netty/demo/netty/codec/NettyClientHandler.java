package cn.wolfcode.netty.demo.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Leon
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到服务器消息:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler 发送数据");
        ctx.writeAndFlush("测试String编解码");
        //测试对象编解码
        User user = new User(1, "123");
//        ctx.writeAndFlush(user);
        //测试用 protostuff 对对象编解码
        ByteBuf buf = Unpooled.copiedBuffer(ProtoStuffUtil.serializer(new User(1, "wolfcode")));
        ctx.writeAndFlush(buf);

    }
}

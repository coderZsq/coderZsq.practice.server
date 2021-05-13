package cn.wolfcode.netty.demo.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Leon
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("NettyMessageEncoder encode 方法被调用");
        // 此处长度用的数据类型时 int，所以固定长度=4
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
//        byte[] serializer = ProtoStuffUtil.serializer(msg);
//        out.writeBytes(serializer);
    }
}

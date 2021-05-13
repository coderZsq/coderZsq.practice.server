package cn.wolfcode.netty.demo.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Leon
 */
public class NettyMessageDecoder extends ByteToMessageDecoder {

    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println();
        System.out.println("NettyMessageDecoder decode 被调用");
        // 需要将得到二进制字节码 -> NettyMessageProtocol 数据包(对象)
        System.out.println(in);

        // 期望读取到的内容长度为 10
        // 由于发生了拆包   int=4,2   剩余未发送过来的数据=8
        // bytebuf=readableBytes=10  ridx=4  widx=14
        if (in.readableBytes() >= 4 || length > 0) {
            if (length == 0) {
                // bytebuf=readableBytes=2  ridx=4  widx=6
                length = in.readInt();
                // length=10
            }
            // readableBytes=10 < length=10
            if (in.readableBytes() < length) {
                System.out.println("当前可读数据不够，继续等待。。");
                return;
            }
            byte[] content = new byte[length];
            // readableBytes=10 < length=10
            if (in.readableBytes() >= length) {
                // bytebuf=readableBytes=10  ridx=4  widx=14
                in.readBytes(content);
                // bytebuf=readableBytes=0  ridx=14  widx=14

                //封装成 NettyMessageProtocol 对象，传递到下一个 handler 业务处理
                NettyMessageProtocol messageProtocol = new NettyMessageProtocol();
                messageProtocol.setLen(length);
                messageProtocol.setContent(content);
                out.add(messageProtocol);
            }
            length = 0;
        }
    }
}

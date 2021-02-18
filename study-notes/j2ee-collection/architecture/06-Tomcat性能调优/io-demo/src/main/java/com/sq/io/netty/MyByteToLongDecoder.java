package com.sq.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Leon
 * @date 2021/1/1
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * decode() 会根据接收的数据，被调用多次，知道确定没有新的元素添加到list,
     * 或者是 ByteBuf 没有更多的可读字节为止。
     * 如果 list 不为空，就会将 list 的内容传递给下一个 handler
     * @param ctx 上下文对象
     * @param in 入站后的 ByteBuf
     * @param out 将解码后的数据传递给下一个 handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // long 类型 为 8 字节
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}

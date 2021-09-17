package io.joyrpc.transport.netty4.handler;

/*-
 * #%L
 * joyrpc
 * %%
 * Copyright (C) 2019 joyrpc.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.codec.Codec;
import io.joyrpc.transport.codec.DefaultDecodeContext;
import io.joyrpc.transport.codec.FixedLengthCodec;
import io.joyrpc.transport.netty4.buffer.NettyChannelBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 解码处理器
 */
public class MessageDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

    /**
     * 函数
     */
    public static final BiFunction<Codec, Channel, ChannelHandler> FUNCTION = (c, l) -> new MessageDecoder(c, l);
    /**
     * 编解码
     */
    protected Codec codec;
    /**
     * 同道
     */
    protected Channel channel;
    /**
     * 固定长度
     */
    protected int fixedLength = -1;

    public MessageDecoder(Codec codec, Channel channel) {
        this.codec = codec;
        this.channel = channel;
        this.fixedLength = (codec instanceof FixedLengthCodec) ? ((FixedLengthCodec) codec).getLength() : -1;
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        super.channelRead(ctx, msg instanceof NettyChannelBuffer ? ((NettyChannelBuffer) msg).getByteBuf() : msg);
    }

    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf in, final List<Object> out) throws Exception {
        if (!in.isReadable()) {
            logger.warn("Bytebuf is not readable when decode!");
            return;
        }
        ByteBuf buf;
        if (fixedLength > 0) {
            buf = in.readableBytes() < fixedLength ? null : in.readRetainedSlice(fixedLength);
        } else {
            buf = in;
        }
        if (buf != null) {
            try {
                Object message = codec.decode(new DefaultDecodeContext(channel), new NettyChannelBuffer(buf));
                if (message != null) {
                    out.add(message);
                }
            } finally {
                if (buf != in) {
                    //readRetainedSlice调用了retain方法，需要release
                    buf.release();
                }
            }
        }
    }

}

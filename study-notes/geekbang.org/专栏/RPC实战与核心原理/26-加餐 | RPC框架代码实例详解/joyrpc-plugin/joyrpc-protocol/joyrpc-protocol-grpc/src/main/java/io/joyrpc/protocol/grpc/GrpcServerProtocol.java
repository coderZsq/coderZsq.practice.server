package io.joyrpc.protocol.grpc;

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

import io.joyrpc.exception.RpcException;
import io.joyrpc.extension.Extension;
import io.joyrpc.extension.condition.ConditionalOnClass;
import io.joyrpc.protocol.AbstractProtocol;
import io.joyrpc.protocol.ServerProtocol;
import io.joyrpc.protocol.grpc.handler.GrpcServerHandler;
import io.joyrpc.protocol.handler.RequestReceiver;
import io.joyrpc.protocol.handler.ResponseReceiver;
import io.joyrpc.protocol.message.MessageHeader;
import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.channel.ChannelChain;
import io.joyrpc.transport.codec.Codec;
import io.joyrpc.transport.codec.Http2Codec;
import io.joyrpc.transport.http2.DefaultHttp2ResponseMessage;
import io.joyrpc.transport.http2.Http2Headers;
import io.joyrpc.transport.http2.Http2ResponseMessage;

import static io.joyrpc.Plugin.MESSAGE_HANDLER_SELECTOR;
import static io.joyrpc.protocol.Protocol.GRPC_ORDER;
import static io.netty.util.CharsetUtil.UTF_8;

/**
 * grpc服务端协议
 */
@Extension(value = GrpcServerProtocol.GRPC_NAME, order = GRPC_ORDER)
@ConditionalOnClass("io.grpc.Codec")
public class GrpcServerProtocol extends AbstractProtocol implements ServerProtocol {

    /**
     * HTTP/2协议头的魔术位
     */
    //在连接上，客户端首先发送一个magic八字节流，这主要适用于客户端从HTTP/1.1升级而来：0x505249202a20485454502f322e300d0a0d0a534d0d0a0d0a
    //解码成ASCII：PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n
    public static final byte[] GRPC_MAGIC_CODE = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n".getBytes(UTF_8);

    public static final String GRPC_NAME = "grpc";

    @Override
    public ChannelChain buildChain() {
        if (chain == null) {
            chain = new ChannelChain()
                    .addLast(new GrpcServerHandler())
                    .addLast(new RequestReceiver<>(MESSAGE_HANDLER_SELECTOR, this::onException))
                    .addLast(new ResponseReceiver());
        }
        return chain;
    }

    @Override
    protected Codec createCodec() {
        return Http2Codec.INSTANCE;
    }

    @Override
    public byte[] getMagicCode() {
        return GRPC_MAGIC_CODE;
    }

    @Override
    protected void onException(final Channel channel, final MessageHeader header, final RpcException cause) {
        int streamId = (Integer) header.getAttributes().get(HeaderMapping.STREAM_ID.getNum());
        int msgId = (int) header.getMsgId();
        Http2Headers endHeaders = Headers.build(cause);
        Http2ResponseMessage message = new DefaultHttp2ResponseMessage(streamId, msgId, null, null, endHeaders, true);
        ackException(channel, header, cause, message);
    }

}

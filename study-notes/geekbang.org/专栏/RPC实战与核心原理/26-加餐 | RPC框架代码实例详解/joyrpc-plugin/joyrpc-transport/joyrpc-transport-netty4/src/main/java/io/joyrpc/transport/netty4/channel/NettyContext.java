package io.joyrpc.transport.netty4.channel;

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
import io.joyrpc.transport.channel.ChannelContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.CompletableFuture;

/**
 * Netty连接通道上下文
 */
public class NettyContext implements ChannelContext {
    /**
     * 连接通道
     */
    protected final Channel channel;
    /**
     * 上下文
     */
    protected final ChannelHandlerContext ctx;

    public NettyContext(Channel channel, ChannelHandlerContext ctx) {
        this.channel = channel;
        this.ctx = ctx;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void fireChannelActive() {
        ctx.fireChannelActive();
    }

    @Override
    public void fireChannelInactive() {
        ctx.fireChannelInactive();
    }

    @Override
    public void fireExceptionCaught(final Throwable cause) {
        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void fireChannelRead(final Object msg) {
        ctx.fireChannelRead(msg);
    }

    @Override
    public CompletableFuture<Void> wrote(Object msg) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        if (msg == null) {
            result.complete(null);
        } else {
            try {
                ctx.writeAndFlush(msg).addListener(new WriteListener(result));
            } catch (Throwable e) {
                result.completeExceptionally(e);
            }
        }
        return result;
    }

    public static NettyContext create(final Channel channel, final ChannelHandlerContext ctx) {
        return new NettyContext(channel, ctx);
    }

    /**
     * 监听器
     */
    protected static class WriteListener implements GenericFutureListener<Future<Void>> {
        /**
         * 目标
         */
        protected final CompletableFuture<Void> target;

        public WriteListener(CompletableFuture<Void> target) {
            this.target = target;
        }

        @Override
        public void operationComplete(Future<Void> future) throws Exception {
            if (future.isSuccess()) {
                target.complete(null);
            } else {
                target.completeExceptionally(future.cause());
            }
        }
    }
}

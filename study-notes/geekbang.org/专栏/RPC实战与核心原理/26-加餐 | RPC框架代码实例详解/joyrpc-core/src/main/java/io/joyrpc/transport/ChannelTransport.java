package io.joyrpc.transport;

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


import io.joyrpc.constants.Constants;
import io.joyrpc.exception.RpcException;
import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.message.Message;
import io.joyrpc.transport.session.Session;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 连接传输通道，用于定义发送消息和获取会话信息等接口
 */
public interface ChannelTransport extends Transport {

    /**
     * 获取连接通道
     *
     * @return 连接通道
     */
    Channel getChannel();

    /**
     * 发送一个消息（不关心响应）
     *
     * @param message 消息
     * @return CompletableFuture
     */
    CompletableFuture<Void> oneway(Message message);

    /**
     * 同步发送一个请求
     *
     * @param message       消息
     * @param timeoutMillis 超时毫秒数
     * @return 应答消息
     */
    default Message sync(final Message message, final int timeoutMillis) throws RpcException, TimeoutException {
        try {
            int timeout = timeoutMillis <= 0 ? Constants.DEFAULT_TIMEOUT : timeoutMillis;
            return message == null ? null : async(message, timeout).get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | RpcException e) {
            throw e;
        } catch (ExecutionException e) {
            throw new RpcException(e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new RpcException(e);
        }
    }

    /**
     * 异步发送一个请求
     *
     * @param message       消息
     * @param timeoutMillis 超时毫秒数
     * @return CompletableFuture
     */
    CompletableFuture<Message> async(Message message, int timeoutMillis);

    /**
     * 获取远程地址
     *
     * @return 远程地址
     */
    InetSocketAddress getRemoteAddress();

    /**
     * 获取最后发送请求成功的时间
     *
     * @return 最后发送请求成功的时间
     */
    long getLastRequestTime();

    /**
     * 返回会话
     *
     * @return 会话
     */
    Session session();

    /**
     * 绑定会话
     *
     * @param session 会话
     * @return 会话
     */
    Session session(Session session);

}

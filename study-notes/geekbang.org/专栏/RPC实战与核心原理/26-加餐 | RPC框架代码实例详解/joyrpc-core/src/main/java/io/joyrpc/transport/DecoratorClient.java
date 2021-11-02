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

import io.joyrpc.event.EventHandler;
import io.joyrpc.event.Publisher;
import io.joyrpc.exception.RpcException;
import io.joyrpc.extension.URL;
import io.joyrpc.protocol.ClientProtocol;
import io.joyrpc.thread.ThreadPool;
import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.channel.ChannelChain;
import io.joyrpc.transport.codec.Codec;
import io.joyrpc.transport.event.TransportEvent;
import io.joyrpc.transport.heartbeat.HeartbeatStrategy;
import io.joyrpc.transport.message.Message;
import io.joyrpc.transport.session.Session;
import io.joyrpc.util.State;

import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/**
 * 装饰客户端
 */
public class DecoratorClient<T extends TransportClient> implements Client {
    /**
     * URL
     */
    protected URL url;
    /**
     * 通道
     */
    protected T transport;
    /**
     * 会话
     */
    protected Session session;
    /**
     * 协议
     */
    protected ClientProtocol protocol;

    public DecoratorClient(T client) {
        this(client == null ? null : client.getUrl(), client);
    }

    public DecoratorClient(URL url, T transport) {
        this.url = Objects.requireNonNull(url, "url can not be null.");
        this.transport = Objects.requireNonNull(transport, "transport can not be null.");
    }

    @Override
    public CompletableFuture<Channel> open() {
        return transport.open();
    }

    @Override
    public CompletableFuture<Channel> close() {
        return transport.close();
    }

    @Override
    public State getState() {
        return transport.getState();
    }

    @Override
    public Channel getChannel() {
        return transport.getChannel();
    }

    @Override
    public CompletableFuture<Void> oneway(final Message message) {
        return transport.oneway(message);
    }

    @Override
    public Message sync(final Message message, final int timoutMillis) throws RpcException, TimeoutException {
        return transport.sync(message, timoutMillis);
    }

    @Override
    public CompletableFuture<Message> async(final Message message, final int timoutMillis) {
        return transport.async(message, timoutMillis);
    }

    @Override
    public void setHeartbeatStrategy(final HeartbeatStrategy heartbeatStrategy) {
        transport.setHeartbeatStrategy(heartbeatStrategy);
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return transport.getRemoteAddress();
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return transport.getLocalAddress();
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public void setChain(final ChannelChain chain) {
        transport.setChain(chain);
    }

    @Override
    public void setCodec(final Codec codec) {
        transport.setCodec(codec);
    }

    @Override
    public ThreadPool getWorkerPool() {
        return transport.getWorkerPool();
    }

    @Override
    public long getLastRequestTime() {
        return transport.getLastRequestTime();
    }

    @Override
    public void addEventHandler(final EventHandler<? extends TransportEvent> handler) {
        transport.addEventHandler(handler);
    }

    @Override
    public void addEventHandler(final EventHandler<? extends TransportEvent>... handlers) {
        if (handlers != null) {
            for (EventHandler eventHandler : handlers) {
                addEventHandler(eventHandler);
            }
        }
    }

    @Override
    public void removeEventHandler(EventHandler<? extends TransportEvent> handler) {
        if (handler != null) {
            transport.removeEventHandler(handler);
        }
    }

    @Override
    public HeartbeatStrategy getHeartbeatStrategy() {
        return transport.getHeartbeatStrategy();
    }

    @Override
    public String getName() {
        return transport.getName();
    }

    @Override
    public Publisher<TransportEvent> getPublisher() {
        return transport.getPublisher();
    }

    @Override
    public Session session() {
        if (session == null) {
            session = transport.session();
        }
        return session;
    }

    @Override
    public Session session(final Session session) {
        this.session = session;
        return transport.session(session);
    }

    @Override
    public int getTransportId() {
        return transport.getTransportId();
    }

    @Override
    public void setProtocol(final ClientProtocol protocol) {
        this.protocol = protocol;
        transport.setProtocol(protocol);
    }

    @Override
    public ClientProtocol getProtocol() {
        if (protocol == null) {
            protocol = transport.getProtocol();
        }
        return protocol;
    }

    @Override
    public int getRequests() {
        return transport.getRequests();
    }
}

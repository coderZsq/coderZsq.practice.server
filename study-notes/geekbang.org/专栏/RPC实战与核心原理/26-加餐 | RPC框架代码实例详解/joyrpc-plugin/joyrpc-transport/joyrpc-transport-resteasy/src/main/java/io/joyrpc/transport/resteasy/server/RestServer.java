package io.joyrpc.transport.resteasy.server;

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

import io.joyrpc.config.AbstractInterfaceConfig;
import io.joyrpc.config.ConfigAware;
import io.joyrpc.config.ProviderConfig;
import io.joyrpc.exception.InitializationException;
import io.joyrpc.extension.URL;
import io.joyrpc.thread.ThreadPool;
import io.joyrpc.transport.DecoratorServer;
import io.joyrpc.transport.TransportFactory;
import io.joyrpc.transport.TransportServer;
import io.joyrpc.transport.channel.ChannelChain;
import io.joyrpc.transport.codec.Codec;
import io.joyrpc.transport.codec.ProtocolDeduction;
import io.joyrpc.transport.resteasy.codec.RestEasyCodec;
import io.joyrpc.transport.resteasy.mapper.ApplicationExceptionMapper;
import io.joyrpc.transport.resteasy.mapper.ClientErrorExceptionMapper;
import io.joyrpc.transport.resteasy.mapper.IllegalArgumentExceptionMapper;
import io.joyrpc.util.Futures;
import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;
import org.jboss.resteasy.plugins.server.resourcefactory.SingletonResource;
import org.jboss.resteasy.spi.ApplicationException;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.util.GetRestful;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.joyrpc.constants.Constants.REST_ROOT;

/**
 * Rest服务
 */
public class RestServer extends DecoratorServer<TransportServer> implements ConfigAware {

    /**
     * 部署
     */
    protected ResteasyDeployment deployment;

    /**
     * 构造函数
     *
     * @param url        url
     * @param factory    传输通道工程
     * @param workerPool 业务线程池
     */
    public RestServer(final URL url, final TransportFactory factory, final ThreadPool workerPool) {
        super(url, null);
        this.transport = factory.createServer(url, workerPool, this::beforeOpen, this::afterClose);
        this.deployment = new ResteasyDeployment();
    }

    /**
     * 启动前钩子
     *
     * @param transport
     * @return
     */
    protected CompletableFuture<Void> beforeOpen(final TransportServer transport) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        String root = url.getString(REST_ROOT);
        root = REST_ROOT.getValue().equals(root) ? "" : root;
        try {
            deployment.start();
            ResteasyProviderFactory providerFactory = deployment.getProviderFactory();
            SynchronousDispatcher dispatcher = (SynchronousDispatcher) deployment.getDispatcher();
            Map<Class<?>, ExceptionMapper> mapperMap = providerFactory.getExceptionMappers();
            mapperMap.put(ApplicationException.class, ApplicationExceptionMapper.mapper);
            mapperMap.put(ClientErrorException.class, ClientErrorExceptionMapper.mapper);
            mapperMap.put(IllegalArgumentException.class, IllegalArgumentExceptionMapper.mapper);
            transport.setCodec(new RestEasyCodec(root, new RequestDispatcher(dispatcher, providerFactory, null)));
            result.complete(null);
        } catch (Throwable e) {
            result.completeExceptionally(e);
        }
        return result;
    }

    /**
     * 关闭后钩子
     *
     * @param transport 传输通道服务
     * @return CompletableFuture
     */
    protected CompletableFuture<Void> afterClose(final TransportServer transport) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        try {
            deployment.stop();
            result.complete(null);
        } catch (Throwable e) {
            result.completeExceptionally(e);
        }
        return result;
    }

    @Override
    public void setChain(ChannelChain chain) {
    }

    @Override
    public void setCodec(Codec codec) {
    }

    @Override
    public void setDeduction(ProtocolDeduction deduction) {
    }

    @Override
    public CompletableFuture<Void> setup(final AbstractInterfaceConfig config) {
        Object ref = ((ProviderConfig) config).getRef();
        Class restful = GetRestful.getRootResourceClass(ref.getClass());
        if (restful != null) {
            restful = GetRestful.getRootResourceClass(ref.getClass());
            deployment.getRegistry().addResourceFactory(new SingletonResource(ref), "/", restful);
            return CompletableFuture.completedFuture(null);
        } else {
            restful = GetRestful.getRootResourceClass(config.getInterfaceClass());
            if (restful == null) {
                return Futures.completeExceptionally(new InitializationException("there is not any @Path in " + config.getInterfaceClazz()));
            } else {
                deployment.getRegistry().addSingletonResource(ref, "/");
                return CompletableFuture.completedFuture(null);
            }
        }
    }
}

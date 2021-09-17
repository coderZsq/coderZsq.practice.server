package io.joyrpc.config;

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

import io.joyrpc.GenericService;
import io.joyrpc.Invoker;
import io.joyrpc.Result;
import io.joyrpc.cluster.candidate.Candidature;
import io.joyrpc.cluster.distribution.*;
import io.joyrpc.cluster.event.NodeEvent;
import io.joyrpc.codec.serialization.Serialization;
import io.joyrpc.config.validator.ValidatePlugin;
import io.joyrpc.constants.Constants;
import io.joyrpc.constants.ExceptionCode;
import io.joyrpc.context.GlobalContext;
import io.joyrpc.context.RequestContext;
import io.joyrpc.context.injection.Transmit;
import io.joyrpc.event.EventHandler;
import io.joyrpc.exception.InitializationException;
import io.joyrpc.exception.RpcException;
import io.joyrpc.extension.MapParametric;
import io.joyrpc.extension.Parametric;
import io.joyrpc.extension.URL;
import io.joyrpc.protocol.message.Invocation;
import io.joyrpc.protocol.message.RequestMessage;
import io.joyrpc.transport.channel.ChannelManagerFactory;
import io.joyrpc.util.*;
import io.joyrpc.util.StateMachine.IntStateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

import static io.joyrpc.GenericService.GENERIC;
import static io.joyrpc.Plugin.TRANSMIT;
import static io.joyrpc.constants.Constants.*;
import static io.joyrpc.util.ClassUtils.forName;
import static io.joyrpc.util.ClassUtils.isReturnFuture;

/**
 * 抽象消费者配置
 */
public abstract class AbstractConsumerConfig<T> extends AbstractInterfaceConfig {
    private final static Logger logger = LoggerFactory.getLogger(AbstractConsumerConfig.class);

    /**
     * 注册中心配置，只能配置一个
     */
    @Valid
    protected RegistryConfig registry;
    /**
     * 直连调用地址
     */
    protected String url;
    /**
     * 是否泛化调用
     */
    protected Boolean generic = false;
    /**
     * 集群处理算法
     */
    @ValidatePlugin(extensible = Router.class, name = "ROUTER", defaultValue = DEFAULT_ROUTER)
    protected String cluster;
    /**
     * 负载均衡算法
     */
    @ValidatePlugin(extensible = LoadBalance.class, name = "LOADBALANCE", defaultValue = DEFAULT_LOADBALANCE)
    protected String loadbalance;
    /**
     * 粘连算法，尽量保持同一个目标地址
     */
    protected Boolean sticky;
    /**
     * 是否jvm内部调用（provider和consumer配置在同一个jvm内，则走本地jvm内部，不走远程）
     */
    protected Boolean injvm;
    /**
     * 是否强依赖（即没有服务节点就启动失败）
     */
    protected Boolean check;
    /**
     * 默认序列化
     */
    @ValidatePlugin(extensible = Serialization.class, name = "SERIALIZATION", defaultValue = DEFAULT_SERIALIZATION)
    protected String serialization;
    /**
     * 初始化连接数
     */
    protected Integer initSize;
    /**
     * 最小连接数
     */
    protected Integer minSize;
    /**
     * 候选者算法插件
     */
    @ValidatePlugin(extensible = Candidature.class, name = "CANDIDATURE", defaultValue = DEFAULT_CANDIDATURE)
    protected String candidature;
    /**
     * 失败最大重试次数
     */
    protected Integer retries;
    /**
     * 每个节点只调用一次
     */
    protected Boolean retryOnlyOncePerNode;
    /**
     * 可以重试的逗号分隔的异常全路径类名
     */
    protected String failoverWhenThrowable;
    /**
     * 重试异常判断接口插件
     */
    @ValidatePlugin(extensible = ExceptionPredication.class, name = "EXCEPTION_PREDICATION")
    protected String failoverPredication;
    /**
     * 异常重试目标节点选择器
     */
    @ValidatePlugin(extensible = FailoverSelector.class, name = "FAILOVER_SELECTOR", defaultValue = DEFAULT_FAILOVER_SELECTOR)
    protected String failoverSelector;
    /**
     * 并行分发数量，在采用并行分发策略有效
     */
    protected Integer forks;
    /**
     * channel创建模式
     * shared:共享(默认),unshared:独享
     */
    @ValidatePlugin(extensible = ChannelManagerFactory.class, name = "CHANNEL_MANAGER_FACTORY", defaultValue = DEFAULT_CHANNEL_FACTORY)
    protected String channelFactory;
    /**
     * 节点选择器
     */
    @ValidatePlugin(extensible = NodeSelector.class, name = "NODE_SELECTOR", multiple = true)
    protected String nodeSelector;
    /**
     * 预热权重
     */
    protected Integer warmupWeight;
    /**
     * 预热时间
     */
    protected Integer warmupDuration;
    /**
     * 泛化调用的类
     */
    protected transient Class<?> genericClass;
    /**
     * 代理实现类
     */
    protected transient volatile T stub;
    /**
     * 事件监听器
     */
    protected transient List<EventHandler<NodeEvent>> eventHandlers = new CopyOnWriteArrayList<>();
    /**
     * 状态机
     */
    protected transient volatile IntStateMachine<Void, ConsumerPilot> stateMachine = new IntStateMachine<>(()->create());

    public AbstractConsumerConfig() {
    }

    public AbstractConsumerConfig(AbstractConsumerConfig config) {
        super(config);
        this.registry = config.registry;
        this.url = config.url;
        this.generic = config.generic;
        this.cluster = config.cluster;
        this.loadbalance = config.loadbalance;
        this.sticky = config.sticky;
        this.injvm = config.injvm;
        this.check = config.check;
        this.serialization = config.serialization;
        this.initSize = config.initSize;
        this.minSize = config.minSize;
        this.candidature = config.candidature;
        this.retries = config.retries;
        this.retryOnlyOncePerNode = config.retryOnlyOncePerNode;
        this.failoverWhenThrowable = config.failoverWhenThrowable;
        this.failoverPredication = config.failoverPredication;
        this.failoverSelector = config.failoverSelector;
        this.forks = config.forks;
        this.channelFactory = config.channelFactory;
        this.nodeSelector = config.nodeSelector;
        this.warmupWeight = config.warmupWeight;
        this.warmupDuration = config.warmupDuration;
    }

    public AbstractConsumerConfig(AbstractConsumerConfig config, String alias) {
        this(config);
        this.alias = alias;
    }

    /**
     * 代理对象，用于Spring场景提前返回代理
     *
     * @return 代理
     */
    public T proxy() {
        if (stub == null) {
            final Class<T> proxyClass = getProxyClass();
            stub = getProxyFactory().getProxy(proxyClass, (proxy, method, args) -> {
                try {
                    ConsumerPilot pilot = stateMachine.getController(s -> s.isOpened());
                    if (pilot == null) {
                        throw new RpcException("Consumer config is not opened. " + name());
                    } else {
                        return pilot.invoke(proxy, method, args);
                    }
                } catch (Throwable e) {
                    if (isReturnFuture(proxyClass, method)) {
                        return Futures.completeExceptionally(e);
                    }
                    throw e;
                }
            });
        }
        return stub;
    }

    @Override
    protected boolean isClose() {
        return stateMachine.isClose(null) || super.isClose();
    }

    protected boolean isClose(final AbstractConsumerPilot<?, ?> controller) {
        return stateMachine.isClose(controller) || super.isClose();
    }

    /**
     * 异步引用
     *
     * @return CompletableFuture
     */
    public CompletableFuture<T> refer() {
        CompletableFuture<T> result = new CompletableFuture<>();
        stateMachine.open().whenComplete((v, e) -> {
            if (e == null) {
                result.complete(stub);
            } else {
                result.completeExceptionally(e);
            }
        });
        return result;
    }

    /**
     * 引用一个远程服务，用于Spring场景，优先返回代理对象
     *
     * @param future 操作结果消费者
     * @return 接口代理类
     * @see AbstractConsumerConfig#refer()
     * @see AbstractConsumerConfig#proxy()
     */
    @Deprecated
    public T refer(final CompletableFuture<Void> future) {
        stateMachine.open().whenComplete((v, e) -> {
            if (e == null) {
                Optional.ofNullable(future).ifPresent(o -> o.complete(null));
            } else {
                Optional.ofNullable(future).ifPresent(o -> o.completeExceptionally(e));
            }
        });

        return stub;


    }

    /**
     * 创建消费控制器
     *
     * @return 控制器
     */
    protected abstract ConsumerPilot create();

    /**
     * 注销
     *
     * @return CompletableFuture
     */
    public CompletableFuture<Void> unrefer() {
        Parametric parametric = new MapParametric<>(GlobalContext.getContext());
        return unrefer(parametric.getBoolean(Constants.GRACEFULLY_SHUTDOWN_OPTION));
    }

    /**
     * 注销
     *
     * @param gracefully 优雅标识
     * @return CompletableFuture
     */
    public CompletableFuture<Void> unrefer(final boolean gracefully) {
        return stateMachine.close(gracefully);
    }

    @Override
    public String name() {
        if (name == null) {
            name = GlobalContext.getString(PROTOCOL_KEY) + "://" + interfaceClazz + "?" + Constants.ALIAS_OPTION.getName() + "=" + alias + "&" + Constants.ROLE_OPTION.getName() + "=" + Constants.SIDE_CONSUMER;
        }
        return name;
    }

    @Override
    public Class getProxyClass() {
        if (Boolean.TRUE.equals(generic)) {
            if (genericClass == null) {
                //获取泛化类名，便于兼容历史版本
                String className = GlobalContext.getString(GENERIC_CLASS);
                if (className == null || className.isEmpty()) {
                    genericClass = GenericService.class;
                } else {
                    try {
                        Class<?> clazz = forName(className);
                        genericClass = GENERIC.test(clazz) ? clazz : GenericService.class;
                    } catch (ClassNotFoundException e) {
                        genericClass = GenericService.class;
                    }
                }
            }
            return genericClass;

        }
        return super.getInterfaceClass();
    }

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    @Override
    public boolean isSubscribe() {
        return subscribe;
    }

    @Override
    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Boolean getRetryOnlyOncePerNode() {
        return retryOnlyOncePerNode;
    }

    public void setRetryOnlyOncePerNode(Boolean retryOnlyOncePerNode) {
        this.retryOnlyOncePerNode = retryOnlyOncePerNode;
    }

    public String getFailoverWhenThrowable() {
        return failoverWhenThrowable;
    }

    public void setFailoverWhenThrowable(String failoverWhenThrowable) {
        this.failoverWhenThrowable = failoverWhenThrowable;
    }

    public String getFailoverPredication() {
        return failoverPredication;
    }

    public void setFailoverPredication(String failoverPredication) {
        this.failoverPredication = failoverPredication;
    }

    public String getFailoverSelector() {
        return failoverSelector;
    }

    public void setFailoverSelector(String failoverSelector) {
        this.failoverSelector = failoverSelector;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public Boolean getGeneric() {
        return generic;
    }

    public void setGeneric(Boolean generic) {
        this.generic = generic;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public Boolean getInjvm() {
        return injvm;
    }

    public void setInjvm(Boolean injvm) {
        this.injvm = injvm;
    }

    public String getNodeSelector() {
        return nodeSelector;
    }

    public void setNodeSelector(String nodeSelector) {
        this.nodeSelector = nodeSelector;
    }

    public Integer getInitSize() {
        return initSize;
    }

    public void setInitSize(Integer initSize) {
        this.initSize = initSize;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public String getCandidature() {
        return candidature;
    }

    public void setCandidature(String candidature) {
        this.candidature = candidature;
    }

    public String getChannelFactory() {
        return channelFactory;
    }

    public void setChannelFactory(String channelFactory) {
        this.channelFactory = channelFactory;
    }

    public T getStub() {
        return stub;
    }

    public Integer getWarmupWeight() {
        return warmupWeight;
    }

    public void setWarmupWeight(Integer warmupWeight) {
        this.warmupWeight = warmupWeight;
    }

    public Integer getWarmupDuration() {
        return warmupDuration;
    }

    public void setWarmupDuration(Integer warmupDuration) {
        this.warmupDuration = warmupDuration;
    }

    /**
     * 添加事件监听器
     *
     * @param handler 事件处理器
     */
    public void addEventHandler(final EventHandler<NodeEvent> handler) {
        if (handler != null) {
            eventHandlers.add(handler);
        }
    }

    /**
     * 移除事件监听器
     *
     * @param handler 事件处理器
     */
    public void removeEventHandler(final EventHandler<NodeEvent> handler) {
        if (handler != null) {
            eventHandlers.remove(handler);
        }
    }

    @Override
    protected Map<String, String> addAttribute2Map(Map<String, String> params) {
        super.addAttribute2Map(params);
        if (url != null) {
            try {
                addElement2Map(params, Constants.URL_OPTION, URL.encode(url));
            } catch (UnsupportedEncodingException e) {
                throw new InitializationException("Value of \"url\" value is not encode in consumer config with key " + url + " !", ExceptionCode.COMMON_URL_ENCODING);
            }
        }
        addElement2Map(params, Constants.GENERIC_OPTION, generic);
        addElement2Map(params, Constants.ROUTER_OPTION, cluster);
        addElement2Map(params, Constants.RETRIES_OPTION, retries);
        addElement2Map(params, Constants.RETRY_ONLY_ONCE_PER_NODE_OPTION, retryOnlyOncePerNode);
        addElement2Map(params, Constants.FAILOVER_WHEN_THROWABLE_OPTION, failoverWhenThrowable);
        addElement2Map(params, Constants.FAILOVER_PREDICATION_OPTION, failoverPredication);
        addElement2Map(params, Constants.FAILOVER_SELECTOR_OPTION, failoverSelector);
        addElement2Map(params, Constants.FORKS_OPTION, forks);
        addElement2Map(params, Constants.LOADBALANCE_OPTION, loadbalance);
        addElement2Map(params, Constants.IN_JVM_OPTION, injvm);
        addElement2Map(params, Constants.STICKY_OPTION, sticky);
        addElement2Map(params, Constants.CHECK_OPTION, check);
        addElement2Map(params, Constants.SERIALIZATION_OPTION, serialization);
        addElement2Map(params, Constants.NODE_SELECTOR_OPTION, nodeSelector);
        addElement2Map(params, Constants.INIT_SIZE_OPTION, initSize);
        addElement2Map(params, Constants.MIN_SIZE_OPTION, minSize);
        addElement2Map(params, Constants.CANDIDATURE_OPTION, candidature);
        addElement2Map(params, Constants.ROLE_OPTION, Constants.SIDE_CONSUMER);
        addElement2Map(params, Constants.TIMESTAMP_KEY, String.valueOf(SystemClock.now()));
        addElement2Map(params, Constants.CHANNEL_FACTORY_OPTION, channelFactory);
        addElement2Map(params, Constants.WARMUP_ORIGIN_WEIGHT_OPTION, warmupWeight);
        addElement2Map(params, Constants.WARMUP_DURATION_OPTION, warmupDuration);
        return params;
    }

    /**
     * 消费者控制器接口
     */
    protected interface ConsumerPilot extends InvocationHandler, StateController<Void> {

    }

    /**
     * 控制器
     */
    protected static abstract class AbstractConsumerPilot<T, C extends AbstractConsumerConfig<T>>
            extends AbstractController<C> implements ConsumerPilot, EventHandler<StateEvent> {

        /**
         * 注册中心配置
         */
        protected RegistryConfig registry;
        /**
         * 注册中心地址
         */
        protected URL registryUrl;
        /**
         * 用于注册的地址
         */
        protected URL registerUrl;
        /**
         * 代理类
         */
        protected Class<?> proxyClass;
        /**
         * 调用handler
         */
        protected volatile ConsumerInvocationHandler invocationHandler;
        /**
         * 等待open结束，invokeHandler初始化
         */
        protected CountDownLatch latch = new CountDownLatch(1);

        /**
         * 构造函数
         *
         * @param config 配置
         */
        public AbstractConsumerPilot(C config) {
            super(config);
        }

        @Override
        public void handle(final StateEvent event) {
            Throwable e = event.getThrowable();
            //控制器事件
            switch (event.getType()) {
                case StateEvent.START_OPEN:
                    GlobalContext.getContext();
                    logger.info(String.format("Start refering consumer %s with bean id %s", config.name(), config.id));
                    break;
                case StateEvent.SUCCESS_OPEN:
                    logger.info(String.format("Success refering consumer %s with bean id %s", config.name(), config.id));
                    //触发配置更新
                    update();
                    break;
                case StateEvent.FAIL_OPEN_ILLEGAL_STATE:
                    logger.error(String.format("Error occurs while referring %s with bean id %s,caused by state is illegal. ", config.name(), config.id));
                    break;
                case StateEvent.FAIL_OPEN:
                    logger.error(String.format("Error occurs while referring %s with bean id %s,caused by %s. ", config.name(), config.id, e.getMessage()), e);
                    break;
                case StateEvent.START_CLOSE:
                    logger.info(String.format("Start unrefering consumer %s with bean id %s", config.name(), config.id));
                    break;
                case StateEvent.SUCCESS_CLOSE:
                    logger.info("Success unrefering consumer " + config.name());
                    break;
            }
        }

        @Override
        protected boolean isClose() {
            return config.isClose(this);
        }

        @Override
        protected boolean isOpened() {
            return config.stateMachine.isOpened(this);
        }

        /**
         * 打开
         *
         * @return CompletableFuture
         */
        public CompletableFuture<Void> open() {
            CompletableFuture<Void> future = new CompletableFuture<>();
            try {
                registry = (config.url != null && !config.url.isEmpty()) ?
                        new RegistryConfig(Constants.FIX_REGISTRY, config.url) :
                        (config.registry != null ? config.registry : RegistryConfig.DEFAULT_REGISTRY_SUPPLIER.get());
                config.validate();
                if (config.registry != registry) {
                    //做一下注册中心验证
                    registry.validate();
                }
                //代理接口
                proxyClass = config.getProxyClass();
                //注册中心地址
                registryUrl = parse(registry);
                String host = getLocalHost(registryUrl.getString(Constants.ADDRESS_OPTION));
                //构造原始URL，调用远程的真实接口名称
                url = new URL(GlobalContext.getString(PROTOCOL_KEY), host, 0, config.getInterfaceTarget(), config.addAttribute2Map());
                //加上动态配置的服务URL
                serviceUrl = configure(null);
                doOpen().whenComplete((v, e) -> {
                    if (e == null) {
                        future.complete(null);
                    } else {
                        future.completeExceptionally(e);
                    }
                });
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
            return future;
        }

        /**
         * 打开操作
         *
         * @return CompletableFuture
         */
        protected abstract CompletableFuture<Void> doOpen();

        /**
         * 关闭
         *
         * @return CompletableFuture
         */
        public CompletableFuture<Void> close() {
            Parametric parametric = new MapParametric<>(GlobalContext.getContext());
            return close(parametric.getBoolean(Constants.GRACEFULLY_SHUTDOWN_OPTION));
        }

        @Override
        public CompletableFuture<Void> close(boolean gracefully) {
            return CompletableFuture.completedFuture(null);
        }

        @Override
        protected CompletableFuture<Void> update(final URL newUrl) {
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            ConsumerInvocationHandler handler = invocationHandler;
            if (handler == null) {
                State state = config.stateMachine.getState();
                if (state.isOpened()) {
                    handler = invocationHandler;
                    if (handler == null) {
                        throw new RpcException("Consumer config is opening. " + config.name());
                    }
                } else if (state.isClosed()) {
                    throw new RpcException("Consumer config is closed. " + config.name());
                } else if (state.isClosing()) {
                    throw new RpcException("Consumer config is closing. " + config.name());
                } else if (state.isOpening()) {
                    //等待初始化
                    latch.await();
                }
            }
            return handler.invoke(proxy, method, args);
        }

    }

    /**
     * 消费者调用
     */
    protected static class ConsumerInvocationHandler implements InvocationHandler {
        /**
         * The Method name toString.
         */
        static String METHOD_NAME_TO_STRING = "toString";
        /**
         * The Method name hashcode.
         */
        static String METHOD_NAME_HASHCODE = "hashCode";
        /**
         * The Method name getClass.
         */
        static String METHOD_NAME_GET_CLASS = "getClass";
        /**
         * The Method name equals.
         */
        static String METHOD_NAME_EQUALS = "equals";
        /**
         * The Invoker.
         */
        protected Invoker invoker;
        /**
         * 接口名称
         */
        protected Class<?> iface;
        /**
         * 是否为异步
         */
        protected boolean async;
        /**
         * 是否是泛化调用
         */
        protected boolean generic;
        /**
         * 默认方法构造器
         */
        protected volatile Constructor<MethodHandles.Lookup> constructor;
        /**
         * 默认方法处理器
         */
        protected Map<String, Optional<MethodHandle>> handles = new ConcurrentHashMap<>();
        /**
         * 透传插件
         */
        protected Iterable<Transmit> transmits = TRANSMIT.reverse();

        /**
         * 构造函数
         *
         * @param invoker    调用器
         * @param iface      接口类
         * @param serviceUrl 服务url
         */
        public ConsumerInvocationHandler(final Invoker invoker, final Class<?> iface, final URL serviceUrl) {
            this.invoker = invoker;
            this.iface = iface;
            this.async = serviceUrl.getBoolean(Constants.ASYNC_OPTION);
            this.generic = GENERIC.test(iface);
        }

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] param) throws Throwable {
            //Java8允许在接口上定义静态方法和默认方法（仅用与GenericService接口类及其子接口类）
            if (generic && method.isDefault()) {
                return invokeDefaultMethod(proxy, method, param);
            }
            //Java8允许在接口上定义静态方法
            if (Modifier.isStatic(method.getModifiers())) {
                //静态方法
                return method.invoke(proxy, param);
            }
            //处理toString，equals，hashcode等方法
            Object obj = invokeObjectMethod(proxy, method, param);
            if (obj != null) {
                return obj;
            }

            boolean isReturnFuture = isReturnFuture(iface, method);
            boolean isAsync = this.async || isReturnFuture;
            //请求上下文
            RequestContext context = RequestContext.getContext();
            //调用之前链路是否为异步
            boolean isAsyncBefore = context.isAsync();
            //上下文的异步必须设置成completeFuture
            context.setAsync(isReturnFuture);
            //构造请求消息，参数类型放在Refer里面设置，使用缓存避免每次计算加快性能
            Invocation invocation = new Invocation(iface, null, method, param, generic);
            RequestMessage<Invocation> request = RequestMessage.build(invocation);
            //分组Failover调用，需要在这里设置创建时间和超时时间，不能再Refer里面。否则会重置。
            request.setCreateTime(SystemClock.now());
            //超时时间为0，Refer会自动修正，便于分组重试
            request.getHeader().setTimeout(0);
            //当前线程
            request.setThread(Thread.currentThread());
            //当前线程上下文
            request.setContext(context);
            //消费端
            request.setConsumer(true);
            //实际的方法名称
            if (generic) {
                request.setMethodName(param[0] == null ? null : param[0].toString());
                if (request.getMethodName() == null || request.getMethodName().isEmpty()) {
                    //泛化调用没有传递方法名称
                    throw new IllegalArgumentException(String.format("the method argument of GenericService.%s can not be empty.", method.getName()));
                }
            } else {
                request.setMethodName(method.getName());
            }
            //初始化请求，绑定方法选项
            invoker.setup(request);
            //调用
            Object response = doInvoke(invoker, request, isAsync);
            try {
                if (isAsync) {
                    if (isReturnFuture) {
                        //方法返回值为 future
                        return response;
                    } else {
                        //手动异步
                        context.setFuture((CompletableFuture<?>) response);
                        return null;
                    }
                } else {
                    // 返回同步结果
                    return response;
                }
            } finally {
                //重置异步标识，防止影响同一context下的provider业务逻辑以及其他consumer
                context.setAsync(isAsyncBefore);
            }
        }

        /**
         * 处理toString，equals，hashcode等方法
         *
         * @param proxy  代理
         * @param method 方法
         * @param param  参数
         * @return 结果
         */
        protected Object invokeObjectMethod(final Object proxy, final Method method, final Object[] param) {
            Object[] args = param;
            String name = method.getName();
            int count = args == null ? 0 : args.length;
            if (generic && count == 2) {
                //判断是$asyc和$invoke方法
                args = (Object[]) param[2];
                name = (String) param[0];
            }
            if (count == 0) {
                if (METHOD_NAME_TO_STRING.equals(name)) {
                    return proxy.getClass().getName() + "@" + Integer.toHexString(invoker.hashCode());
                } else if (METHOD_NAME_HASHCODE.equals(name)) {
                    return invoker.hashCode();
                } else if (METHOD_NAME_GET_CLASS.equals(name)) {
                    return proxy.getClass();
                }
            } else if (count == 1 && METHOD_NAME_EQUALS.equals(name)) {
                return invoker.equals(args[0]);
            }
            return null;
        }

        /**
         * 调用默认方法
         *
         * @param proxy  代理
         * @param method 方法
         * @param param  参数
         * @return 结果
         * @throws Throwable 异常
         */
        protected Object invokeDefaultMethod(final Object proxy, final Method method, final Object[] param) throws Throwable {
            if (constructor == null) {
                synchronized (this) {
                    if (constructor == null) {
                        constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                        constructor.setAccessible(true);
                    }
                }
            }
            if (constructor != null) {
                Optional<MethodHandle> optional = handles.computeIfAbsent(method.getName(), o -> {
                    Class<?> declaringClass = method.getDeclaringClass();
                    try {
                        return Optional.of(constructor.
                                newInstance(declaringClass, MethodHandles.Lookup.PRIVATE).
                                unreflectSpecial(method, declaringClass).
                                bindTo(proxy));
                    } catch (Throwable e) {
                        return Optional.empty();
                    }
                });
                if (optional.isPresent()) {
                    return optional.get().invokeWithArguments(param);
                }
            }
            throw new UnsupportedOperationException();
        }

        /**
         * 这个方法用来做 Trace 追踪的增强点，不要随便修改
         *
         * @param invoker 调用器
         * @param request 请求
         * @param async   异步标识
         * @return 返回值
         * @throws Throwable 异常
         */
        protected Object doInvoke(Invoker invoker, RequestMessage<Invocation> request, boolean async) throws Throwable {
            return async ? asyncInvoke(invoker, request) : syncInvoke(invoker, request);
        }

        /**
         * 同步调用
         *
         * @param invoker 调用器
         * @param request 请求
         * @return 返回值
         * @throws Throwable 异常
         */
        protected Object syncInvoke(final Invoker invoker, final RequestMessage<Invocation> request) throws Throwable {
            try {
                CompletableFuture<Result> future = invoker.invoke(request);
                //正常同步返回，处理Java8的future.get内部先自循环造成的性能问题
                Result result = future.get(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
                if (result.isException()) {
                    throw result.getException();
                }
                return result.getValue();
            } catch (CompletionException | ExecutionException e) {
                throw e.getCause() != null ? e.getCause() : e;
            } finally {
                //调用结束，使用新的请求上下文，保留会话、调用者和跟踪的上下文
                transmits.forEach(o -> o.onReturn(request));
            }
        }

        /**
         * 异步调用
         *
         * @param invoker 调用器
         * @param request 请求
         * @return 返回值
         */
        protected Object asyncInvoke(final Invoker invoker, final RequestMessage<Invocation> request) {
            //异步调用，业务逻辑执行完毕，不清理IO线程的上下文
            CompletableFuture<Object> response = new CompletableFuture<>();
            try {
                CompletableFuture<Result> future = invoker.invoke(request);
                future.whenComplete((res, err) -> {
                    //目前是让用户自己保留上下文
                    Throwable throwable = err == null ? res.getException() : err;
                    if (throwable != null) {
                        transmits.forEach(o -> o.onComplete(request, new Result(request.getContext(), throwable)));
                        response.completeExceptionally(throwable);
                    } else {
                        transmits.forEach(o -> o.onComplete(request, res));
                        response.complete(res.getValue());
                    }
                });
            } catch (CompletionException e) {
                //调用出错，线程没有切换，保留原有上下文
                transmits.forEach(o -> o.onComplete(request, new Result(request.getContext(), e)));
                response.completeExceptionally(e.getCause() != null ? e.getCause() : e);
            } catch (Throwable e) {
                //调用出错，线程没有切换，保留原有上下文
                transmits.forEach(o -> o.onComplete(request, new Result(request.getContext(), e)));
                response.completeExceptionally(e);
            } finally {
                //调用结束，使用新的请求上下文，保留会话、调用者和跟踪的上下文
                transmits.forEach(o -> o.onReturn(request));
            }
            return response;
        }

    }


}

package io.joyrpc.invoker;

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

import io.joyrpc.InvokerAware;
import io.joyrpc.cluster.Cluster;
import io.joyrpc.cluster.discovery.config.ConfigHandler;
import io.joyrpc.cluster.discovery.config.Configure;
import io.joyrpc.cluster.discovery.registry.Registry;
import io.joyrpc.cluster.distribution.LoadBalance;
import io.joyrpc.cluster.distribution.loadbalance.StickyLoadBalance;
import io.joyrpc.cluster.event.NodeEvent;
import io.joyrpc.codec.serialization.Registration;
import io.joyrpc.codec.serialization.Serialization;
import io.joyrpc.config.ConsumerConfig;
import io.joyrpc.config.ProviderConfig;
import io.joyrpc.constants.Constants;
import io.joyrpc.context.GlobalContext;
import io.joyrpc.event.Publisher;
import io.joyrpc.event.PublisherConfig;
import io.joyrpc.exception.IllegalConfigureException;
import io.joyrpc.extension.MapParametric;
import io.joyrpc.extension.Parametric;
import io.joyrpc.extension.URL;
import io.joyrpc.metric.DashboardAware;
import io.joyrpc.metric.DashboardFactory;
import io.joyrpc.permission.SerializerTypeScanner;
import io.joyrpc.protocol.ServerProtocol;
import io.joyrpc.protocol.handler.DefaultProtocolDeduction;
import io.joyrpc.thread.NamedThreadFactory;
import io.joyrpc.thread.ThreadPool;
import io.joyrpc.thread.ThreadPoolFactory;
import io.joyrpc.transport.ChannelTransport;
import io.joyrpc.transport.EndpointFactory;
import io.joyrpc.transport.Server;
import io.joyrpc.transport.ShareServer;
import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.event.InactiveEvent;
import io.joyrpc.transport.message.Message;
import io.joyrpc.util.Close;
import io.joyrpc.util.Futures;
import io.joyrpc.util.Shutdown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static io.joyrpc.Plugin.*;
import static io.joyrpc.cluster.Cluster.EVENT_PUBLISHER_CLUSTER;
import static io.joyrpc.cluster.Cluster.EVENT_PUBLISHER_CLUSTER_CONF;
import static io.joyrpc.constants.Constants.*;
import static io.joyrpc.constants.ExceptionCode.CONSUMER_DUPLICATE_REFER;
import static io.joyrpc.constants.ExceptionCode.PROVIDER_DUPLICATE_EXPORT;
import static io.joyrpc.permission.SerializerWhiteList.addGlobalWhite;
import static io.joyrpc.util.Futures.whenComplete;

/**
 * 服务管理器
 *
 * @date: 9/1/2019
 */
public class ServiceManager {

    private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);

    public static final String HASH_CODE = "hashCode";

    protected static final String EVENT_PUBLISHER_GROUP = "event.invoker";
    protected static final String EVENT_PUBLISHER_NAME = "default";
    protected static final PublisherConfig EVENT_PUBLISHER_CONF = PublisherConfig.builder().timeout(1000).build();

    /**
     * 全局的生成器
     */
    public static final ServiceManager INSTANCE = new ServiceManager();
    /**
     * 事件通知器
     */
    protected Publisher<ExporterEvent> publisher;
    /**
     * 业务服务引用
     */
    protected Map<String, Refer> refers = new ConcurrentHashMap<>();
    /**
     * 系统内置服务引用，需要最后关闭
     */
    protected Map<String, Refer> systems = new ConcurrentHashMap<>();
    /**
     * 业务服务输出
     */
    protected ExporterManager exports = createExporterManager();
    /**
     * 共享的TCP服务
     */
    protected Map<Integer, Server> servers = new ConcurrentHashMap<>(10);
    /**
     * 集群管理器
     */
    protected CallbackManager callbackManager = new CallbackManager();
    /**
     * 接口ID对照表，兼容老版本数据结构
     */
    protected Map<Long, String> interfaceIds = new ConcurrentHashMap<>();

    protected Map<Class<?>, Boolean> registers = new ConcurrentHashMap<>();

    protected ServiceManager() {
        Shutdown.addHook(new Shutdown.HookAdapter((Shutdown.Hook) this::close, 0));
        this.publisher = EVENT_BUS.get().getPublisher(EVENT_PUBLISHER_GROUP, EVENT_PUBLISHER_NAME, EVENT_PUBLISHER_CONF);
        this.publisher.start();
    }

    /**
     * 创建服务管理器
     * @return 服务管理器
     */
    protected ExporterManager.MapExporterManager createExporterManager() {
        return new ExporterManager.MapExporterManager();
    }

    /**
     * 添加接口ID对照
     *
     * @param interfaceId
     * @param className
     */
    public static void putInterfaceId(final long interfaceId, final String className) {
        INSTANCE.interfaceIds.put(interfaceId, className);
    }

    /**
     * 根据接口ID获取接口名称
     *
     * @param interfaceId long
     * @return
     */
    public static String getClassName(final long interfaceId) {
        return INSTANCE.interfaceIds.get(interfaceId);
    }

    /**
     * 根据接口ID获取接口名称
     *
     * @param interfaceId String
     * @return
     */
    public static String getClassName(final String interfaceId) {
        try {
            return INSTANCE.interfaceIds.get(Long.parseLong(interfaceId));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取接口ID映射
     *
     * @return
     */
    public static Map<Long, String> getInterfaceIds() {
        return new HashMap<>(INSTANCE.interfaceIds);
    }

    /**
     * 创建引用对象
     *
     * @param config
     * @param url
     * @param registry
     * @param registerUrl
     * @param configure
     * @param subscribeUrl
     * @param configHandler
     * @return
     */
    public static Refer refer(final URL url,
                              final ConsumerConfig<?> config,
                              final Registry registry,
                              final URL registerUrl,
                              final Configure configure,
                              final URL subscribeUrl,
                              final ConfigHandler configHandler) {
        return INSTANCE.doRefer(url, config, registry, registerUrl, configure, subscribeUrl, configHandler);
    }


    /**
     * 创建引用对象
     *
     * @param url           服务URL
     * @param config        服务配置
     * @param registries    注册中心
     * @param registerUrls  注册URL
     * @param configure     动态配置
     * @param subscribeUrl  订阅配置URL
     * @param configHandler 配置变更处理器
     * @return
     */
    public static Exporter export(final URL url,
                                  final ProviderConfig<?> config,
                                  final List<Registry> registries,
                                  final List<URL> registerUrls,
                                  final Configure configure,
                                  final URL subscribeUrl,
                                  final ConfigHandler configHandler) {
        return INSTANCE.doExport(url, config, registries, registerUrls, configure, subscribeUrl, configHandler);
    }

    /**
     * 获取所有引用对象
     *
     * @return
     */
    public static List<Refer> getRefers() {
        return new ArrayList<>(INSTANCE.refers.values());
    }

    /**
     * 迭代服务
     *
     * @param consumer 消费者
     */
    public static void exports(final Consumer<Exporter> consumer) {
        INSTANCE.exports.foreach(consumer);
    }

    /**
     * 遍历服务
     *
     * @param className 接口名称
     * @param consumer  消费者
     */
    public static void exports(final String className, final Consumer<Exporter> consumer) {
        INSTANCE.exports.foreach(className, consumer);
    }

    /**
     * 获取输出服务
     *
     * @param className 接口名称
     * @param alias     分组
     * @param port      端口
     * @return 输出服务
     */
    public static Exporter getExporter(final String className, final String alias, final int port) {
        return INSTANCE.exports.get(className, alias, port);
    }

    /**
     * 根据接口名称获取输出服务
     *
     * @param className 接口名称
     * @param alias     分组
     * @return 输出服务
     */
    public static Exporter getFirstExporter(final String className, final String alias) {
        return INSTANCE.exports.getFirst(className, alias);
    }

    public static Server getServer(final int port) {
        return INSTANCE.servers.get(port);
    }

    /**
     * 获取服务
     *
     * @return
     */
    public static List<Server> getServers() {
        return new ArrayList<>(INSTANCE.servers.values());
    }

    /**
     * 获取回调线程池
     *
     * @return
     */
    public static ThreadPool getCallbackPool() {
        return INSTANCE.callbackManager.getThreadPool();
    }

    /**
     * 获取消费者回调容器
     *
     * @return
     */
    public static CallbackContainer getConsumerCallback() {
        return INSTANCE.callbackManager.getConsumer();
    }

    /**
     * 获取服务提供者回调容器
     *
     * @return
     */
    public static CallbackContainer getProducerCallback() {
        return INSTANCE.callbackManager.getProducer();
    }

    /**
     * 创建引用对象
     *
     * @param url
     * @param config
     * @param registry
     * @param registerUrl
     * @param configure
     * @param subscribeUrl
     * @param configHandler
     * @param <T>
     * @return
     */
    protected <T> Refer doRefer(final URL url,
                                final ConsumerConfig<T> config,
                                final Registry registry,
                                final URL registerUrl,
                                final Configure configure,
                                final URL subscribeUrl,
                                final ConfigHandler configHandler) {
        return doRefer(url, config, registry, registerUrl, configure, subscribeUrl, configHandler,
                url.getBoolean(Constants.SYSTEM_REFER_OPTION) ? systems : refers);
    }

    /**
     * 创建引用对象
     *
     * @param url           服务URL
     * @param config        配置
     * @param registry      注册中心
     * @param registerUrl   注册URL
     * @param configure     配置中心
     * @param subscribeUrl  配置订阅URL
     * @param configHandler 配置变更处理器
     * @param refers
     * @return
     */
    protected Refer doRefer(final URL url,
                            final ConsumerConfig<?> config,
                            final Registry registry,
                            final URL registerUrl,
                            final Configure configure,
                            final URL subscribeUrl,
                            final ConfigHandler configHandler,
                            final Map<String, Refer> refers) {
        //一个服务接口可以注册多次，每个的参数不一样
        String key = url.remove(TIMESTAMP_KEY).toString(false, true);
        //添加hashCode参数，去掉HOST减少字符串
        String clusterName = url.add(HASH_CODE, key.hashCode()).setHost(null).
                toString(false, true, Constants.ALIAS_OPTION.getName(), Constants.COUNTER, HASH_CODE);

        if (refers.containsKey(clusterName)) {
            throw new IllegalConfigureException(
                    String.format("Duplicate consumer config with key %s has been referred.", clusterName),
                    CONSUMER_DUPLICATE_REFER);
        }

        return refers.computeIfAbsent(clusterName, o -> {
            //负载均衡
            LoadBalance loadBalance = buildLoadbalance(config, url);
            //面板工厂
            DashboardFactory dashboardFactory = buildDashboardFactory(url, loadBalance);
            //集群的名字是服务名称+别名+配置变更计数器，确保相同接口引用的集群名称不一样
            Publisher<NodeEvent> publisher = EVENT_BUS.get().getPublisher(EVENT_PUBLISHER_CLUSTER, clusterName, EVENT_PUBLISHER_CLUSTER_CONF);
            //TODO 是否要设置业务线程池处理应答消息
            ThreadPool workerPool = null;
            Cluster cluster = new Cluster(clusterName, url, registry, null, null, workerPool, null, dashboardFactory, METRIC_HANDLER.extensions(), publisher);
            //判断是否有回调，如果注册成功，说明有回调方法，需要往Cluster注册事件，监听节点断开事件
            serializationRegister(config.getProxyClass());
            //refer的名称和key保持一致，便于删除
            return new Refer(clusterName, url, config, registry, registerUrl, configure, subscribeUrl, configHandler,
                    cluster, loadBalance, callbackManager.getConsumer(), this.publisher,
                    ServiceManager::getFirstExporter,
                    (v, t) -> refers.remove(v.getName()));
        });
    }

    /**
     * 构建统计面板工厂
     *
     * @param url         url
     * @param loadBalance 负载均衡
     * @return 统计面板工厂
     */
    protected DashboardFactory buildDashboardFactory(final URL url, final LoadBalance loadBalance) {
        //自适应负载均衡、熔断都需要统计面板
        return loadBalance instanceof DashboardAware
                || url.getBoolean(CIRCUIT_BREAKER_ENABLE, false)
                || url.getBoolean(DASHBOARD_ENABLE, false) ? DASHBOARD_FACTORY.get() : null;
    }

    /**
     * 构建路由器
     *
     * @param config 配置
     * @param url    url
     * @return 负载均衡
     */
    protected <T> LoadBalance buildLoadbalance(final ConsumerConfig<T> config, final URL url) {
        //负载均衡
        boolean sticky = url.getBoolean(STICKY_OPTION);
        LoadBalance loadBalance = LOADBALANCE.get(url.getString(Constants.LOADBALANCE_OPTION));
        loadBalance.setUrl(url);
        if (loadBalance instanceof InvokerAware) {
            InvokerAware aware = (InvokerAware) loadBalance;
            //获取真实接口名称
            aware.setClassName(url.getPath());
        }
        loadBalance.setup();
        loadBalance = sticky ? new StickyLoadBalance(loadBalance) : loadBalance;

        return loadBalance;
    }

    /**
     * 注册序列化
     *
     * @param clazz 类
     */
    protected void serializationRegister(final Class<?> clazz) {
        //多个消费者指向同一个类，避免重复扫描注册类
        if (registers.computeIfAbsent(clazz, c -> Boolean.TRUE)) {
            //扫描接口类，将入参、返回值、异常 加入白名单
            Set<Class<?>> targets = new SerializerTypeScanner(clazz).scan();
            addGlobalWhite(targets);
            //注册序列化逻辑
            Iterable<Serialization> itr = SERIALIZATION.extensions();
            for (Serialization ser : itr) {
                if (Registration.class.isAssignableFrom(ser.getClass())) {
                    ((Registration) ser).register(targets);
                }
            }
        }
    }

    /**
     * 创建引用对象
     *
     * @param url           服务URL
     * @param config        服务提供者配置
     * @param registries    注册中心
     * @param registerUrls  注册的URL
     * @param configure     配置中心
     * @param subscribeUrl  订阅配置的URL
     * @param configHandler 配置处理器
     * @return
     */
    protected Exporter doExport(final URL url,
                                final ProviderConfig<?> config,
                                final List<Registry> registries,
                                final List<URL> registerUrls,
                                final Configure configure,
                                final URL subscribeUrl,
                                final ConfigHandler configHandler) {
        //使用url.getPath获取真实接口名称
        final String name = EXPORTER_NAME_FUNC.apply(url.getPath(), config.getAlias());
        Exporter exists = getExporter(url.getPath(), config.getAlias(), url.getPort());
        if (exists != null) {
            throw new IllegalConfigureException(
                    String.format("Duplicate provider config with key %s has been exported.", name),
                    PROVIDER_DUPLICATE_EXPORT);
        }
        return exports.add(url.getPath(), config.getAlias(), url.getPort(), () -> {
            serializationRegister(config.getProxyClass());
            return new Exporter(name, url, config, registries, registerUrls, configure, subscribeUrl, configHandler,
                    getServer(url), callbackManager.getProducer(), publisher, c -> exports.remove(c));
        });
    }

    /**
     * 获取服务
     *
     * @param url url
     * @return 服务
     */
    protected Server getServer(final URL url) {
        return servers.computeIfAbsent(url.getPort(), port -> {
            EndpointFactory factory = ENDPOINT_FACTORY.getOrDefault(url.getString(ENDPOINT_FACTORY_OPTION));
            //每个server独立的线程池
            Server server = factory.createServer(url, getWorkerPool(url));
            server.setDeduction(new DefaultProtocolDeduction());
            server.addEventHandler(event -> {
                //连接通道关闭事件
                if (event instanceof InactiveEvent) {
                    Channel channel = ((InactiveEvent) event).getChannel();
                    ChannelTransport transport = channel.getAttribute(Channel.CHANNEL_TRANSPORT);
                    //移除回调
                    callbackManager.getProducer().removeCallback(transport);
                }
            });
            return new ShareServer(server, v -> servers.remove(v.getUrl().getPort()));
        });
    }

    /**
     * 创建业务线程池
     *
     * @param url url
     * @return 线程池
     */
    protected ThreadPool getWorkerPool(final URL url) {
        ThreadPoolFactory pool = THREAD_POOL.getOrDefault(url.getString(THREADPOOL_OPTION));
        String name = "RPC-BZ-" + url.getPort();
        NamedThreadFactory threadFactory = new NamedThreadFactory(name, true);
        return pool.get(name, url, threadFactory);
    }

    /**
     * 获取回调管理器
     *
     * @return
     */
    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    /**
     * 关闭
     *
     * @return
     */
    public CompletableFuture<Void> close() {
        Parametric parametric = new MapParametric(GlobalContext.getContext());
        return close(parametric.getBoolean(Constants.GRACEFULLY_SHUTDOWN_OPTION));
    }

    /**
     * 关闭
     *
     * @param gracefully 是否优雅关闭
     * @return
     */
    public CompletableFuture<Void> close(final boolean gracefully) {
        publisher.close();
        CompletableFuture<Void> result = new CompletableFuture<>();
        //保存所有的注册中心
        Set<Registry> registries = new HashSet<>(5);
        Parametric parametric = new MapParametric(GlobalContext.getContext());
        long timeout = parametric.getPositiveLong(OFFLINE_TIMEOUT_OPTION);
        //广播下线消息
        offline(timeout, gracefully).whenComplete((k, s) ->
                //关闭服务
                closeInvoker(registries, gracefully).whenComplete((v, t) -> {
                    //安全关闭后，关闭集群
                    exports = createExporterManager();
                    refers = new ConcurrentHashMap<>();
                    callbackManager.close();
                    //关闭服务
                    servers.forEach((o, r) -> whenComplete(r.close(), () -> Close.close(r.getWorkerPool(), 0)));
                    servers = new ConcurrentHashMap<>();
                    //关闭系统内容消费者（如：注册中心消费者）
                    systems.forEach((o, r) -> r.close());
                    //关闭注册中心
                    closeRegistry(registries, gracefully).whenComplete((o, r) -> result.complete(null));
                }));
        return result;
    }

    /**
     * 广播下线消息，超时时间5秒
     *
     * @param timeout    优雅关闭等待的超时时间
     * @param gracefully 是否优雅关闭
     * @return
     */
    protected CompletableFuture<Void> offline(final long timeout, final boolean gracefully) {
        CompletableFuture<Void> result = new CompletableFuture<>();
        if (!gracefully) {
            //不广播下线消息
            result.complete(null);
        } else {
            List<CompletableFuture<Void>> futures = new LinkedList<>();
            //发送下线消息，这个有点慢
            servers.forEach((k, server) -> server.forEach(t -> {
                ServerProtocol protocol = t.getChannel().getAttribute(Channel.PROTOCOL);
                if (protocol != null) {
                    Message message = protocol.offline(server.getUrl());
                    if (message != null) {
                        futures.add(t.oneway(message));
                    }
                }
            }));
            if (timeout > 0) {
                //等到，确保下线消息通知到客户端
                CompletableFuture<Void> future = Futures.allOf(futures);
                //启动线程判断超时
                future = Futures.timeout(future, timeout);
                future.whenComplete((v, t) -> result.complete(null));
            }
        }
        return result;
    }

    /**
     * 关闭消费者和服务提供者
     *
     * @param registries
     * @param gracefully
     * @return
     */
    protected CompletableFuture<Void> closeInvoker(final Set<Registry> registries, final boolean gracefully) {
        List<CompletableFuture<Void>> futures = new LinkedList<>();
        //关闭消费者和服务提供者
        exports(exporter -> {
            futures.add(exporter.getConfig().unexport(gracefully));
            registries.addAll(exporter.getRegistries());
        });
        //非注册中心消费者
        refers.forEach((k, v) -> {
            futures.add(v.getConfig().unrefer(gracefully));
            registries.add(v.getRegistry());
        });
        return gracefully ? Futures.allOf(futures) : CompletableFuture.completedFuture(null);
    }

    /**
     * 关闭消注册中心
     *
     * @param registries
     * @param gracefully
     * @return
     */
    protected CompletableFuture<Void> closeRegistry(final Set<Registry> registries, final boolean gracefully) {
        List<CompletableFuture<Void>> futures = new ArrayList<>(registries.size());
        registries.forEach(o -> futures.add(o.close()));
        return gracefully ? Futures.allOf(futures) : CompletableFuture.completedFuture(null);
    }

}

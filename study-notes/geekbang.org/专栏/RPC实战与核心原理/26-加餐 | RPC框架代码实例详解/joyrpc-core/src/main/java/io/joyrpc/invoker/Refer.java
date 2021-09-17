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
import io.joyrpc.Result;
import io.joyrpc.cluster.Candidate;
import io.joyrpc.cluster.Cluster;
import io.joyrpc.cluster.ClusterAware;
import io.joyrpc.cluster.Node;
import io.joyrpc.cluster.discovery.config.ConfigHandler;
import io.joyrpc.cluster.discovery.config.Configure;
import io.joyrpc.cluster.discovery.registry.Registry;
import io.joyrpc.cluster.distribution.LoadBalance;
import io.joyrpc.cluster.distribution.NodeSelector;
import io.joyrpc.cluster.distribution.Router;
import io.joyrpc.cluster.distribution.loadbalance.adaptive.AdaptiveScorer;
import io.joyrpc.cluster.event.NodeEvent;
import io.joyrpc.config.ConsumerConfig;
import io.joyrpc.config.InterfaceOption;
import io.joyrpc.config.InterfaceOption.ConsumerMethodOption;
import io.joyrpc.constants.Constants;
import io.joyrpc.context.GlobalContext;
import io.joyrpc.context.RequestContext;
import io.joyrpc.context.injection.NodeReqInjection;
import io.joyrpc.event.EventHandler;
import io.joyrpc.event.Publisher;
import io.joyrpc.exception.NoAliveProviderException;
import io.joyrpc.exception.ShutdownExecption;
import io.joyrpc.exception.TransportException;
import io.joyrpc.extension.MapParametric;
import io.joyrpc.extension.Parametric;
import io.joyrpc.extension.URL;
import io.joyrpc.protocol.ClientProtocol;
import io.joyrpc.protocol.Protocol.MessageConverter;
import io.joyrpc.protocol.message.Invocation;
import io.joyrpc.protocol.message.MessageHeader;
import io.joyrpc.protocol.message.RequestMessage;
import io.joyrpc.protocol.message.ResponsePayload;
import io.joyrpc.transport.Client;
import io.joyrpc.transport.Server;
import io.joyrpc.transport.message.Message;
import io.joyrpc.transport.session.DefaultSession;
import io.joyrpc.transport.session.Session;
import io.joyrpc.util.Futures;
import io.joyrpc.util.Shutdown;
import io.joyrpc.util.SystemClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static io.joyrpc.Plugin.*;
import static io.joyrpc.constants.Constants.*;
import static io.joyrpc.constants.ExceptionCode.CONSUMER_NO_ALIVE_PROVIDER;
import static io.joyrpc.util.StringUtils.split;
import static io.joyrpc.util.Timer.timer;

/**
 * 引用
 *
 * @date: 2019/1/10
 */
public class Refer extends AbstractService {
    private static final Logger logger = LoggerFactory.getLogger(Refer.class);
    /**
     * 本地消息ID
     */
    protected AtomicInteger localMsgId = new AtomicInteger(0);
    /**
     * 消费配置
     */
    protected ConsumerConfig<?> config;
    /**
     * 注册中心
     */
    protected Registry registry;
    /**
     * 往注册中心订阅注册的URL
     */
    protected URL registerUrl;
    /**
     * 集群
     */
    protected Cluster cluster;
    /**
     * 负载均衡
     */
    protected LoadBalance loadBalance;
    /**
     * 配置变更处理器
     */
    protected ConfigHandler configHandler;
    /**
     * 路由节点选择器
     */
    protected NodeSelector[] nodeSelectors;
    /**
     * 回调容器
     */
    protected CallbackContainer container;
    /**
     * 关闭的消费者
     */
    protected BiConsumer<Refer, ? super Throwable> closing;
    /**
     * 是否优先本地JVM调用
     */
    protected boolean inJvm;
    /**
     * 本地服务的名称
     */
    protected String exporterName;

    /**
     * 条件透传
     */
    protected Iterable<NodeReqInjection> injections;
    /**
     * 分发异常处理插件
     */
    protected Iterable<ExceptionHandler> exceptionHandlers;
    /**
     * 事件通知器
     */
    protected Publisher<ExporterEvent> publisher;
    /**
     * 本地的服务
     */
    protected volatile Exporter local;
    /**
     * 本地的服务列表
     */
    protected Set<Exporter> locals = new CopyOnWriteArraySet<>();
    /**
     * 本地服务事件处理器
     */
    protected EventHandler<ExporterEvent> localHandler = this::onEvent;

    /**
     * 构造函数
     *
     * @param name          名称
     * @param url           url
     * @param config        配置
     * @param registry      注册中心
     * @param configure     配置中心
     * @param subscribeUrl  订阅URL
     * @param configHandler 配置处理器
     * @param cluster       集群
     * @param loadBalance   负载均衡
     * @param container     回调容器
     * @param publisher     消息总线
     * @param localFunc     本地服务函数
     * @param closing       关闭函数
     */
    protected Refer(final String name,
                    final URL url,
                    final ConsumerConfig<?> config,
                    final Registry registry,
                    final URL registerUrl,
                    final Configure configure,
                    final URL subscribeUrl,
                    final ConfigHandler configHandler,
                    final Cluster cluster,
                    final LoadBalance loadBalance,
                    final CallbackContainer container,
                    final Publisher<ExporterEvent> publisher,
                    final BiFunction<String, String, Exporter> localFunc,
                    final BiConsumer<Refer, ? super Throwable> closing) {
        this.name = name;
        this.url = url;
        this.config = config;
        this.registry = registry;
        this.registerUrl = registerUrl;
        this.configure = configure;
        this.subscribeUrl = subscribeUrl;
        //保留全局的配置变更处理器
        this.configHandler = configHandler;
        this.cluster = cluster;
        this.loadBalance = loadBalance;
        this.container = container;
        this.closing = closing;
        this.system = url.getBoolean(Constants.SYSTEM_OPTION);
        //别名
        this.alias = url.getString(Constants.ALIAS_OPTION);
        //代理接口
        this.interfaceClass = config.getProxyClass();
        //真实接口名称
        this.interfaceName = url.getPath();

        this.inJvm = url.getBoolean(Constants.IN_JVM_OPTION);
        this.exporterName = EXPORTER_NAME_FUNC.apply(interfaceName, alias);
        //节点选择器
        this.nodeSelectors = buildSelectors();
        //方法选项
        this.option = INTERFACE_OPTION_FACTORY.get().create(interfaceClass, interfaceName, url, this::configure,
                loadBalance instanceof AdaptiveScorer ? (method, cfg) -> ((AdaptiveScorer) loadBalance).score(cluster, method, cfg) : null);
        //有回调函数
        if (option.isCallback()) {
            cluster.addHandler(event -> {
                if (event.getType() == NodeEvent.EventType.DISCONNECT) {
                    Object payload = event.getPayload();
                    //删除Transport上的回调
                    Client client = payload instanceof Client ? (Client) payload : event.getNode().getClient();
                    //删除Callback
                    List<CallbackInvoker> callbacks = container.removeCallback(client);
                    if (!Shutdown.isShutdown() && cluster.isOpened()) {
                        //没有关机和集群没有销毁则重新callback
                        callbacks.forEach(invoker -> invoker.recallback());
                    }
                }
            });
        }

        this.cluster.addHandler(config);
        //处理链
        this.chain = FILTER_CHAIN_FACTORY.getOrDefault(url.getString(FILTER_CHAIN_FACTORY_OPTION))
                .build(this, this::distribute);
        this.injections = NODE_REQUEST_INJECTION.extensions(NodeReqInjection::test);
        this.exceptionHandlers = EXCEPTION_HANDLER.extensions();
        this.publisher = publisher;
        this.publisher.addHandler(localHandler);
        Exporter provider = localFunc.apply(interfaceName, alias);
        if (provider != null) {
            local = provider;
            //有本地节点，不需要等到节点建连成功
            cluster.setCheck(false);
            logger.info("Bind to local provider " + exporterName);
        }
    }

    /**
     * 调用远程
     *
     * @param node    当前节点
     * @param last    前一个节点
     * @param request 请求
     * @return CompletableFuture
     */
    protected CompletableFuture<Result> invokeRemote(final Node node, final Node last, final RequestMessage<Invocation> request) {
        Client client = node == null ? null : node.getClient();
        if (client == null) {
            //选择完后，节点可能被其它线程断开连接了
            return Futures.completeExceptionally(new TransportException("Error occurs while sending message. caused by client is null.", true));
        }
        //捕获内部异常，可能在重试线程里面调用，用户线程异常捕获不了异常
        try {
            //header 使用协商结果
            MessageHeader header = request.getHeader();
            header.copy(client.session());
            //条件透传注入
            for (NodeReqInjection injection : injections) {
                if (last != null) {
                    //取消上一个节点注入的参数
                    injection.reject(request, last);
                }
                //注入当前节点参数
                injection.inject(request, node);
            }
            //绑定回调，调用异常会删除注册的callback，避免造成垃圾数据
            if (request.getOption().getCallback() != null) {
                container.addCallback(request, client);
            }
            //异步发起调用
            CompletableFuture<Message> future = client.async(request, header.getTimeout());

            //返回future
            return future.handle((response, err) -> {
                //线程恢复统一改在consumerInvokerHandler里面
                Result result = err != null ? new Result(request.getContext(), err, response) : response2Result(request, client, response);
                if (result.isException()) {
                    //异常处理
                    onException(request, result, client);
                }
                return result;
            });
        } catch (Throwable e) {
            return Futures.completeExceptionally(e);
        }
    }

    /**
     * 把应答消息转换成调用结果
     *
     * @param request  请求
     * @param client   客户端
     * @param response 应答消息
     * @return 调用结果
     */
    protected Result response2Result(final RequestMessage<Invocation> request, final Client client, final Message response) {
        ClientProtocol protocol = client.getProtocol();
        //拿到Response对象
        ResponsePayload payLoad = (ResponsePayload) response.getPayLoad();
        //返回值为空或为void，response可能为空
        if (payLoad == null) {
            payLoad = new ResponsePayload();
            response.setPayLoad(payLoad);
        }
        //根据协议拿到应答消息转换器，在网关调用会用到
        MessageConverter converter = protocol.inMessage();
        BiFunction<Message, Object, Object> function = converter == null ? null : converter.response();
        //构造返回值
        Result result = payLoad.isError() ?
                new Result(request.getContext(), payLoad.getException(), response) :
                new Result(request.getContext(), function == null ? payLoad.getResponse() :
                        function.apply(request, payLoad.getResponse()), response);
        return result;
    }

    /**
     * 出了异常
     *
     * @param request 请求
     * @param result  结果
     * @param client  客户端
     */
    protected void onException(final RequestMessage<Invocation> request, final Result result, final Client client) {
        CallbackMethod callback = request.getOption().getCallback();
        if (callback != null) {
            //失败注销callback
            MessageHeader header = request.getHeader();
            container.removeCallback((String) header.getAttribute(Constants.HEAD_CALLBACK_INSID));
        }
        //处理异常
        if (exceptionHandlers != null) {
            exceptionHandlers.forEach(h -> h.handle(client, result.getException()));
        }
    }

    /**
     * 配置路由器
     *
     * @return 路由节点选择器
     */
    protected NodeSelector[] buildSelectors() {
        String value = url.getString(NODE_SELECTOR_OPTION);
        if (value != null && !value.isEmpty()) {
            String[] parts = split(value, ',');
            List<NodeSelector> selectors = new ArrayList<>(parts.length);
            NodeSelector selector;
            for (int i = 0; i < parts.length; i++) {
                selector = NODE_SELECTOR.get(parts[i]);
                if (selector != null) {
                    selector.setUrl(url);
                    selector.setClass(interfaceClass);
                    selector.setClassName(interfaceName);
                    selector.setup();
                    selectors.add(selector);
                }
            }
            return selectors.toArray(new NodeSelector[0]);
        }
        return null;
    }


    /**
     * 配置分发策略
     *
     * @param router 分发策略
     * @return 分发策略
     */
    protected Router configure(final Router router) {
        if (router != null) {
            router.setUrl(url);
            router.setLoadBalance(loadBalance);
            router.setOperation(this::invokeRemote);
            router.setup();
        }
        return router;
    }

    @Override
    public void setup(final RequestMessage<Invocation> request) {
        //实际的方法名称，泛型调用进行了处理
        ConsumerMethodOption option = (ConsumerMethodOption) this.option.getOption(request.getMethodName());
        option.setAutoScore(true);
        request.setOption(option);
        //避免分组重试重复调用
        if (request.getCreateTime() <= 0) {
            request.setCreateTime(SystemClock.now());
        }
        //用缓存的信息设置参数类型，加快性能
        InterfaceOption.ArgType argType = option.getArgType();
        //已经设置了class和method对象
        Invocation invocation = request.getPayLoad();
        invocation.setAlias(alias);
        invocation.setObject(config.getStub());
        if (argType != null) {
            //泛化调用为null
            invocation.setArgsType(argType.getClasses(), argType.getTypes());
        }
        //设置实际的类名，而不是泛化类
        invocation.setClassName(interfaceName);
        //方法透传参数，整合了接口级别的参数
        invocation.addAttachments(option.getImplicits());
        //透传处理
        transmits.forEach(o -> o.inject(request));
        //超时时间放在后面，Invocation已经注入了请求上下文参数，隐藏参数等等
        if (request.getHeader().getTimeout() <= 0) {
            Parametric parametric = new MapParametric(invocation.getAttachments());
            int timeout = parametric.getPositive(TIMEOUT_KEY, option.getTimeout());
            //超时时间
            request.setTimeout(timeout);
            request.getHeader().setTimeout(timeout);
        }
    }

    /**
     * 分发数据
     *
     * @param request 请求
     * @return 结果
     */
    protected CompletableFuture<Result> distribute(final RequestMessage<Invocation> request) {
        //需要重新设置一下超时时间，防止过滤器占用时间过多
        if (!request.decline()) {
            return Futures.completeExceptionally(new TimeoutException(String.format("It's timeout to invoke %s.%s", interfaceName, request.getMethodName())));
        }
        if (inJvm) {
            //本地调用
            CompletableFuture<Result> result = distribute2Local(request, local);
            if (result != null) {
                return result;
            }
        }
        //集群节点
        List<Node> nodes = cluster.getNodes();
        if (!nodes.isEmpty() && nodeSelectors != null) {
            //节点选择
            Candidate candidate = new Candidate(cluster, null, nodes, nodes.size());
            NodeSelector selector;
            int count = 0;
            //遍历选择器，筛选节点
            for (int i = 0; i < nodeSelectors.length; i++) {
                selector = nodeSelectors[i];
                if (selector != null) {
                    nodes = selector.select(count == 0 ? candidate : new Candidate(candidate, nodes), request);
                    count++;
                }
            }
        }
        if (nodes == null || nodes.isEmpty()) {
            //节点为空
            throw new NoAliveProviderException(
                    String.format("No alive provider found. class=%s alias=%s", interfaceName, alias),
                    CONSUMER_NO_ALIVE_PROVIDER);
        }
        Router route = ((ConsumerMethodOption) request.getOption()).getRouter();
        return route.route(request, new Candidate(cluster, null, nodes, nodes.size()));
    }

    /**
     * 本地分发
     *
     * @param request 请求
     * @param local   本地服务
     * @return 结果
     */
    protected CompletableFuture<Result> distribute2Local(final RequestMessage<Invocation> request, final Exporter local) {
        if (local == null) {
            return null;
        }
        Server server = local.getServer();
        InetSocketAddress localAddress = server.getLocalAddress();
        request.setLocalAddress(localAddress);
        request.setRemoteAddress(localAddress);
        //构造会话
        DefaultSession session = new DefaultSession();
        session.setAuthenticated(Session.AUTH_SESSION_SUCCESS);
        session.put(KEY_APPID, GlobalContext.getString(KEY_APPID));
        session.put(KEY_APPNAME, GlobalContext.getString(KEY_APPNAME));
        session.put(KEY_APPINSID, GlobalContext.getString(KEY_APPINSID));
        session.put(KEY_APPGROUP, GlobalContext.getString(KEY_APPGROUP));
        //创建服务端请求
        final RequestMessage<Invocation> newRequest = new RequestMessage<>();
        newRequest.setTimeout(request.getTimeout());
        newRequest.setCreateTime(request.getCreateTime());
        newRequest.setReceiveTime(SystemClock.now());
        newRequest.setLocalAddress(localAddress);
        newRequest.setRemoteAddress(localAddress);
        newRequest.setThread(Thread.currentThread());
        newRequest.setMethodName(request.getMethodName());
        //本地调用，直接认证成功
        newRequest.setAuthenticated(s -> Session.AUTH_SESSION_SUCCESS);
        newRequest.setAuthorization(r -> Boolean.TRUE);
        newRequest.setPayLoad(request.getPayLoad().create());
        newRequest.setContext(new RequestContext());
        newRequest.setSession(session);
        //透传处理
        transmits.forEach(o -> o.onServerReceive(newRequest));
        local.setup(newRequest);

        //产生本地调用的消息ID
        MessageHeader header = request.getHeader();
        header.setMsgId(localMsgId.incrementAndGet());
        final CompletableFuture<Result> result = new CompletableFuture<>();
        //超时判断
        timer().add(FUTURE_TIMEOUT_PREFIX + request.getMsgId(), SystemClock.now() + header.getTimeout(), () -> {
            if (!result.isDone()) {
                result.completeExceptionally(new TimeoutException(String.format("It's timeout to invoke %s.%s", interfaceName, request.getMethodName())));
            }
        });
        //异步执行
        server.runAsync(() -> {
            //恢复服务端上下文
            newRequest.restore(() -> local.invoke(newRequest).whenComplete((r, err) -> {
                if (err != null) {
                    result.completeExceptionally(err);
                } else {
                    result.complete(r);
                }
            }));
        });
        return result;
    }

    @Override
    protected CompletableFuture<Void> doOpen() {
        CompletableFuture<Void> result = new CompletableFuture<>();
        //注册
        register().whenComplete((v, t) -> {
            if (t == null) {
                logger.info("Success register consumer config " + name);
            }
        });
        //打开集群不需要等到注册成功，因为可以从本地文件恢复。打开之前，已经提前进行了订阅获取全局配置
        return cluster.open();
    }

    @Override
    protected CompletableFuture<Void> doClose() {
        option.close();
        publisher.removeHandler(localHandler);
        //注销节点事件
        cluster.removeHandler(config);
        //从注册中心注销，最多重试1次
        CompletableFuture<Void> future1 = deregister().whenComplete((v, t) -> logger.info("Success deregister consumer config " + name));
        //取消配置订阅
        CompletableFuture<Void> future2 = unsubscribe().whenComplete((v, t) -> logger.info("Success unsubscribe consumer config " + name));
        //关闭集群
        CompletableFuture<Void> future3 = cluster.close().whenComplete((v, error) -> {
            logger.info("Success close cluster " + name);
        });
        //关闭过滤链
        CompletableFuture<Void> future4 = chain.close().whenComplete((v, t) -> logger.info("Success close filter chain " + name));

        return CompletableFuture.allOf(future1, future2, future3, future4).whenComplete((v, t) -> {
            if (closing != null) {
                closing.accept(this, null);
            }
        });
    }

    /**
     * 注册
     *
     * @return CompletableFuture
     */
    protected CompletableFuture<Void> register() {
        CompletableFuture<Void> result = new CompletableFuture<>();
        //注册
        if (registerUrl != null) {
            //URL里面注册的类是实际的interfaceClass，不是proxyClass
            registry.register(registerUrl).whenComplete((v, t) -> {
                if (t == null) {
                    result.complete(null);
                } else {
                    result.completeExceptionally(t);
                }
            });
        } else {
            result.complete(null);
        }
        return result;
    }

    /**
     * 从注册中心注销
     *
     * @return CompletableFuture
     */
    protected CompletableFuture<Void> deregister() {
        CompletableFuture<Void> future = new CompletableFuture<>();
        if (registerUrl != null) {
            //URL里面注册的类是实际的interfaceClass，不是proxyClass
            //TODO 要确保各个注册中心实现在服务有问题的情况下，能快速的注销掉
            registry.deregister(registerUrl, 1).whenComplete((r, t) -> future.complete(null));
        } else {
            future.complete(null);
        }
        return future;
    }

    /**
     * 取消配置订阅
     *
     * @return CompletableFuture
     */
    protected CompletableFuture<Void> unsubscribe() {
        CompletableFuture<Void> future = new CompletableFuture<>();
        //订阅
        if (subscribeUrl != null) {
            // todo 不能保证执行成功
            configure.unsubscribe(subscribeUrl, configHandler);
        }
        future.complete(null);
        return future;
    }

    @Override
    protected Throwable shutdownException() {
        return new ShutdownExecption("Refer is shutdown.", false);
    }

    /**
     * 事件处理器
     *
     * @param event 事件
     */
    protected void onEvent(final ExporterEvent event) {
        if (!event.name.equals(exporterName)) {
            return;
        }
        switch (event.getType()) {
            case INITIAL:
            case OPEN:
                locals.add(event.getExporter());
                if (local == null) {
                    local = event.getExporter();
                    cluster.setCheck(false);
                    logger.info("Bind to local provider " + exporterName);
                }
                break;
            case CLOSE:
                locals.remove(event.getExporter());
                if (local == event.getExporter()) {
                    local = locals.isEmpty() ? null : locals.iterator().next();
                    if (local == null) {
                        logger.info("Change to remote provider " + exporterName);
                    }
                }
                break;
        }
    }

    @Override
    protected void setup(final InvokerAware target) {
        if (target instanceof ClusterAware) {
            ((ClusterAware) target).setCluster(cluster);
        }
        super.setup(target);
    }

    public Cluster getCluster() {
        return cluster;
    }

    public ConsumerConfig<?> getConfig() {
        return config;
    }

    public Registry getRegistry() {
        return registry;
    }

    public InterfaceOption getOption() {
        return option;
    }
}

package io.joyrpc.cluster;

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

import io.joyrpc.cluster.Node.NodeHandler;
import io.joyrpc.cluster.Shard.ShardState;
import io.joyrpc.cluster.candidate.Candidature;
import io.joyrpc.cluster.discovery.naming.ClusterHandler;
import io.joyrpc.cluster.discovery.naming.Registar;
import io.joyrpc.cluster.event.ClusterEvent;
import io.joyrpc.cluster.event.ClusterEvent.ShardEvent;
import io.joyrpc.cluster.event.MetricEvent;
import io.joyrpc.cluster.event.NodeEvent;
import io.joyrpc.cluster.filter.NodeFilter;
import io.joyrpc.constants.Constants;
import io.joyrpc.event.EventHandler;
import io.joyrpc.event.Publisher;
import io.joyrpc.event.PublisherConfig;
import io.joyrpc.exception.AuthenticationException;
import io.joyrpc.exception.InitializationException;
import io.joyrpc.exception.ProtocolException;
import io.joyrpc.exception.TransportException;
import io.joyrpc.extension.URL;
import io.joyrpc.extension.URLOption;
import io.joyrpc.metric.Dashboard;
import io.joyrpc.metric.Dashboard.DashboardType;
import io.joyrpc.metric.DashboardFactory;
import io.joyrpc.thread.ThreadPool;
import io.joyrpc.transport.EndpointFactory;
import io.joyrpc.transport.message.Message;
import io.joyrpc.util.*;
import io.joyrpc.util.Timer;
import io.joyrpc.util.StateMachine.IntStateMachine;
import io.joyrpc.util.network.Ping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.joyrpc.Plugin.*;
import static io.joyrpc.constants.Constants.CANDIDATURE_OPTION;
import static io.joyrpc.util.StringUtils.toSimpleString;
import static io.joyrpc.util.Timer.timer;

/**
 * 集群
 */
public class Cluster {

    private static final Logger logger = LoggerFactory.getLogger(Cluster.class);
    public static final URLOption<Long> RECONNECT_INTERVAL = new URLOption<>("reconnectInterval", 2000L);
    protected static final AtomicLong idCounter = new AtomicLong();
    public static final String EVENT_PUBLISHER_METRIC = "event.metric";
    public static final PublisherConfig EVENT_PUBLISHER_METRIC_CONF = PublisherConfig.builder().timeout(1000).build();
    public static final String EVENT_PUBLISHER_CLUSTER = "event.cluster";
    public static final PublisherConfig EVENT_PUBLISHER_CLUSTER_CONF = PublisherConfig.builder().timeout(1000).build();
    public static final Function<String, Throwable> THROWABLE_FUNCTION = error -> new InitializationException(error);
    /**
     * 名称
     */
    protected final String name;
    /**
     * URL
     */
    protected final URL url;
    /**
     * 目录服务
     */
    protected final Registar registar;
    /**
     * 选择算法
     */
    protected final Candidature candidature;
    /**
     * 客户端工厂类
     */
    protected final EndpointFactory factory;
    /**
     * 集群业务线程池
     */
    protected final ThreadPool workerPool;
    /**
     * 身份认证提供者
     */
    protected final Function<URL, Message> authentication;
    /**
     * 仪表盘提供者
     */
    protected final DashboardFactory dashboardFactory;
    /**
     * 集群指标通知器
     */
    protected Publisher<MetricEvent> metricPublisher;
    /**
     * 集群节点事件通知器
     */
    protected final Publisher<NodeEvent> clusterPublisher;
    /**
     * 当前集群的指标
     */
    protected final Dashboard dashboard;
    /**
     * 重连时间间隔
     */
    protected final long reconnectInterval;
    /**
     * 选择的最小分片数量
     */
    protected final int minSize;
    /**
     * 达到初始化建连的数量则自动起来
     */
    protected final int initSize;
    /**
     * 初始化超时时间
     */
    protected final long initTimeout;
    /**
     * 初始化连接超时时间
     */
    protected final long initConnectTimeout;
    /**
     * 是否启用SSL
     */
    protected final boolean sslEnable;
    /**
     * 当初始化超时的时候，是否验证必须要有连接
     */
    protected boolean check;
    /**
     * 状态机
     */
    protected final IntStateMachine<Void, ClusterController> stateMachine = new IntStateMachine<>(() -> new ClusterController(this), THROWABLE_FUNCTION);

    public Cluster(final URL url) {
        this(null, url, null, null, null, null, null, null, null, null);
    }

    public Cluster(final URL url, final Registar registar) {
        this(null, url, registar, null, null, null, null, null, null, null);
    }

    public Cluster(final String name, final URL url) {
        this(name, url, null, null, null, null, null, null, null, null);
    }

    public Cluster(final String name, final URL url, final Registar registar) {
        this(name, url, registar, null, null, null, null, null, null, null);
    }

    public Cluster(final String name,
                   final URL url,
                   final Registar registar,
                   final Candidature candidature,
                   final EndpointFactory factory,
                   final ThreadPool workerPool,
                   final Function<URL, Message> authentication,
                   final DashboardFactory dashboardFactory,
                   final Iterable<? extends MetricHandler> metricHandlers,
                   final Publisher<NodeEvent> clusterPublisher) {
        Objects.requireNonNull(url, "url can not be null.");
        this.name = name == null || name.isEmpty() ? url.toString(false, false) : name;
        //不需要添加环境变量，由外层处理合并
        this.url = url;
        this.registar = Objects.requireNonNull(registar == null ? REGISTAR.get(url.getString("registar", url.getProtocol())) : registar, "registar can not be null.");
        this.candidature = candidature != null ? candidature : CANDIDATURE.get(url.getString(CANDIDATURE_OPTION), CANDIDATURE_OPTION.getValue());
        this.factory = factory != null ? factory : ENDPOINT_FACTORY.getOrDefault(url.getString("endpointFactory"));
        this.workerPool = workerPool;
        this.authentication = authentication;
        this.initSize = url.getInteger(Constants.INIT_SIZE_OPTION);
        this.minSize = url.getInteger(Constants.MIN_SIZE_OPTION);
        this.initTimeout = url.getLong(Constants.INIT_TIMEOUT_OPTION);
        this.initConnectTimeout = url.getLong(Constants.INIT_CONNECT_TIMEOUT_OPTION);
        this.check = url.getBoolean(Constants.CHECK_OPTION);
        this.reconnectInterval = url.getLong(RECONNECT_INTERVAL);
        this.sslEnable = url.getBoolean(Constants.SSL_ENABLE);
        //创建仪表盘
        this.dashboardFactory = dashboardFactory;
        this.dashboard = dashboardFactory != null ? dashboardFactory.create(url, DashboardType.Cluster) : null;
        //构建事件发布器
        this.clusterPublisher = clusterPublisher != null ? clusterPublisher : EVENT_BUS.get().getPublisher(EVENT_PUBLISHER_CLUSTER, this.name, EVENT_PUBLISHER_CLUSTER_CONF);
        //额外的指标监听器
        if (dashboard != null || metricHandlers != null) {
            Iterator<? extends MetricHandler> it = metricHandlers.iterator();
            if (it.hasNext() || dashboard != null) {
                this.metricPublisher = EVENT_BUS.get().getPublisher(EVENT_PUBLISHER_METRIC, String.valueOf(idCounter.incrementAndGet()), EVENT_PUBLISHER_METRIC_CONF);
                this.metricPublisher.addHandler(dashboard);
                this.metricPublisher.addHandler(metricHandlers);
            }
        }
    }

    /**
     * 打开集群
     */
    public CompletableFuture<Void> open() {
        return stateMachine.open();
    }

    /**
     * 关闭集群
     */
    public CompletableFuture<Void> close() {
        return stateMachine.close(false);
    }

    /**
     * 选举
     *
     * @param candidates 候选人
     * @return 选中的结果
     */
    protected Candidature.Result candidate(final List<Node> candidates) {
        return candidature.candidate(url, Candidate.builder().cluster(this).
                region(registar).nodes(candidates).size(minSize).
                build());
    }

    /**
     * 是否发生变更
     *
     * @param shard    分片
     * @param previous 前一个节点
     * @return 变更标识
     */
    protected boolean isChanged(final Shard shard, final Node previous) {
        //没有发生变化
        return previous == null || (previous.originWeight != shard.getWeight()
                || !Objects.equals(previous.getName(), shard.getName())
                || !Objects.equals(previous.getRegion(), shard.getRegion())
                || !Objects.equals(previous.getDataCenter(), shard.getDataCenter())
                || !Objects.equals(previous.getProtocol(), shard.getProtocol())
                || !Objects.equals(previous.getUrl(), shard.getUrl()));
    }

    /**
     * 获取重试时间
     *
     * @param throwable 异常
     * @return 重试时间
     */
    protected long getRetryTime(final Throwable throwable) {
        if (throwable instanceof ProtocolException) {
            //协商失败，最少20秒重连
            return getRetryTime(Math.max(reconnectInterval, 20000L));
        } else if (throwable instanceof AuthenticationException) {
            //认证失败，最少20秒重连
            return getRetryTime(Math.max(reconnectInterval, 20000L));
        } else if (Ping.detectDead(throwable)) {
            //目标节点不存在了，最少20秒重连
            return getRetryTime(Math.max(reconnectInterval, 20000L));
        } else {
            return getRetryTime(reconnectInterval);
        }
    }

    /**
     * 获取重试时间
     *
     * @return 重试时间
     */
    protected long getRetryTime() {
        return getRetryTime(reconnectInterval);
    }

    /**
     * 获取下线后的重连时间
     *
     * @return 重连时间
     */
    protected long getRetryTimeWhenOffline() {
        //最小35秒超时，可以覆盖大量注册中心的超时设置
        return getRetryTime(Math.max(reconnectInterval, 35000L));
    }

    /**
     * 获取重试时间
     *
     * @param interval 时间间隔
     * @return 重试时间
     */
    protected long getRetryTime(final long interval) {
        return SystemClock.now() + interval + ThreadLocalRandom.current().nextInt(1000);
    }

    /**
     * 创建节点
     *
     * @param shard   分片
     * @param handler 事件处理器
     * @return 节点
     */
    protected Node createNode(final Shard shard, final NodeHandler handler) {
        return new Node(name, url, shard,
                factory,
                workerPool,
                authentication,
                handler,
                dashboardFactory == null ? null : dashboardFactory.create(url, DashboardType.Node),
                metricPublisher);
    }

    /**
     * 是否启动
     *
     * @return 启动标识
     */
    public boolean isOpened() {
        return stateMachine.getState().isOpened();
    }

    /**
     * 是否启用了SSL
     *
     * @return 启用SSL标识
     */
    public boolean isSslEnable() {
        return sslEnable;
    }

    /**
     * 添加节点事件处理器
     *
     * @param handler 处理器
     * @return 成功标识
     */
    public boolean addHandler(final EventHandler<NodeEvent> handler) {
        return clusterPublisher.addHandler(handler);
    }

    /**
     * 移除节点事件处理器
     *
     * @param handler 处理器
     * @return 是否成功
     */
    public boolean removeHandler(final EventHandler<NodeEvent> handler) {
        return clusterPublisher.removeHandler(handler);
    }

    /**
     * 获取可用的节点
     *
     * @return the nodes
     */
    public List<Node> getNodes() {
        ClusterController controller = stateMachine.getController();
        return controller == null ? new ArrayList<>(0) : controller.readys;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public URL getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取区域对象
     *
     * @return 地域
     */
    public Region getRegion() {
        return registar;
    }

    /**
     * 设置当初始化超时的时候，是否验证必须要有连接。<br/>
     * 在应用同时提供服务并消费自身的服务时候，在应用优雅启动阶段需要设置为False。
     *
     * @param check 验证标识
     */
    public void setCheck(boolean check) {
        this.check = check;
    }

    /**
     * 控制器<br/>
     * 避免锁，集群事件，节点事件和节点打开回调都放入队列，由定时器单线程执行。<br/>
     * 集群关闭和定时器存在并发访问节点问题
     */
    protected static class ClusterController implements StateController<Void> {
        /**
         * 集群
         */
        protected final Cluster cluster;
        /**
         * 任务队列
         */
        protected final TimerQueue tasks;
        /**
         * 节点，集群关闭和定时器存在并发访问节点问题
         */
        protected final Map<String, Node> nodes = new ConcurrentHashMap<>(50);
        /**
         * 可用节点，集群外要访问，所以每次修改都是拷贝一份
         */
        protected volatile List<Node> readys = new ArrayList<>(0);
        /**
         * 连接好的节点
         */
        protected final Map<String, Node> connects = new HashMap<>(50);
        /**
         * 冷备节点队列
         */
        protected final Queue<DelayedNode> backups = new DelayQueue<>();
        /**
         * 待补充的节点数量
         */
        protected final AtomicInteger supplies = new AtomicInteger(0);
        /**
         * 补充任务的拥有者
         */
        protected final AtomicBoolean supplyOwner = new AtomicBoolean(false);
        /**
         * 就绪触发器
         */
        protected volatile Trigger trigger;
        /**
         * 集群事件处理器
         */
        protected final ClusterHandler clusterHandler = this::onClusterEvent;
        /**
         * 节点事件处理器
         */
        protected final NodeHandler nodeHandler = this::onNodeEvent;
        /**
         * 补充节点的名称
         */
        protected final String supplyTask;

        /**
         * 构造函数
         *
         * @param cluster 集群
         */
        public ClusterController(final Cluster cluster) {
            this.cluster = cluster;
            this.supplyTask = "SupplyTask-" + cluster.name;
            this.tasks = new TimerQueue("ClusterTask-" + cluster.name);
        }

        @Override
        public CompletableFuture<Void> open() {
            CompletableFuture<Void> future = new CompletableFuture<>();
            tasks.open();
            //启动事件监听器
            cluster.clusterPublisher.start();
            Optional.ofNullable(cluster.metricPublisher).ifPresent(Publisher::start);
            //定期触发控制器
            Optional.ofNullable(cluster.dashboard).ifPresent(o -> timer().add(new DashboardTask(cluster, this)));
            //订阅集群
            cluster.registar.subscribe(cluster.url, clusterHandler);
            if (cluster.initSize <= 0) {
                //不需要等到初始化连接，异步通知，避免在Open线程里面触发
                timer().add("ReadyTask-" + cluster.name, SystemClock.now(), () -> future.complete(null));
            } else {
                long beginTime = SystemClock.now();
                this.trigger = new Trigger(cluster.name, cluster.initSize,
                        cluster.initTimeout, cluster.initConnectTimeout, () -> cluster.check,
                        () -> future.complete(null),
                        () -> future.completeExceptionally(new InitializationException(
                                String.format("initialization timeout, used %d ms, maybe has no enough provider nodes.", SystemClock.now() - beginTime))));
            }
            return future;
        }

        @Override
        public void fireClose() {
            Optional.ofNullable(trigger).ifPresent(Trigger::close);
        }

        @Override
        public CompletableFuture<Void> close(final boolean gracefully) {
            cluster.registar.unsubscribe(cluster.url, clusterHandler);
            Close.close(cluster.clusterPublisher);
            Close.close(cluster.metricPublisher);
            Close.close(tasks);
            trigger = null;
            //关闭节点
            closeNodes();
            return CompletableFuture.completedFuture(null);
        }

        /**
         * 执行节点事件
         *
         * @param event 事件
         */
        protected void onNodeEvent(final NodeEvent event) {
            if (!isOpen()) {
                return;
            }
            Node node = event.getNode();
            NodeEvent.EventType type = event.getType();
            switch (type) {
                case CLOSING:
                    //收到服务端下线通知，正在优雅关闭连接中，需要提前从就绪节点列表中删除
                    tasks.offer(() -> onClosing(node));
                    break;
                case OFFLINE:
                    //服务端下线了，则进行关闭
                    logger.info(String.format("%s node %s.", type.getDesc(), node.getName()));
                    tasks.offer(() -> onClose(node, cluster.getRetryTimeWhenOffline()));
                    break;
                case RECONNECT:
                    //重连
                    logger.info(String.format("%s node %s.", type.getDesc(), node.getName()));
                    tasks.offer(() -> onClose(node, 0));
                    break;
                case DISCONNECT:
                    //连接断开了，则进行关闭
                    logger.info(String.format("%s node %s.", type.getDesc(), node.getName()));
                    tasks.offer(() -> onClose(node, cluster.getRetryTime()));
                    break;
            }
            cluster.clusterPublisher.offer(event);
        }

        /**
         * 执行集群事件
         *
         * @param event 事件
         */
        protected void onClusterEvent(final ClusterEvent event) {
            if (event == null || !isOpen()) {
                logger.warn(String.format("Cluster %s receive cluster event, but "
                                + (event == null ? "event is null" : "controller was not opened")
                                + ", cluster status is %s.",
                        this.cluster.name, this.cluster.stateMachine.getState().name()));
                return;
            }
            switch (event.getType()) {
                case CLEAR:
                    //清理
                    tasks.offer(() -> onClearEvent(event));
                    break;
                case UPDATE:
                    tasks.offer(() -> onUpdateEvent(event));
                    break;
                case FULL:
                    //全量更新
                    tasks.offer(() -> onFullEvent(event));
                    break;
            }
        }


        /**
         * 删除所有分片事件，如果注册中心权限认证失败会收到该事件
         *
         * @param event 事件
         */
        protected void onClearEvent(final ClusterEvent event) {
            backups.clear();
            connects.clear();
            readys = new ArrayList<>(0);
            closeNodes();
            nodes.clear();
        }

        /**
         * 全量更新事件
         *
         * @param event 事件
         */
        protected void onFullEvent(final ClusterEvent event) {
            int add = 0;
            List<ShardEvent> events = event.getDatum();
            if (events != null) {
                //记录节点名称
                Set<String> names = new HashSet<>();
                //遍历节点，进行添加
                for (ShardEvent e : events) {
                    //防止注册中心有重复数据的情况
                    if (names.add(e.getShard().getName())) {
                        add += onAdd(e.getShard()) ? 1 : 0;
                    }
                }
                //判断哪些节点被删除了
                for (Map.Entry<String, Node> node : nodes.entrySet()) {
                    if (!names.contains(node.getKey())) {
                        onDelete(node.getValue());
                    }
                }
            }
            if (add > 0) {
                //新增了节点，重新选举
                candidate();
            }
            //第一次全量事件，触发连接超时检测
            if (trigger != null) {
                trigger.onFull(add);
            }
        }

        /**
         * 增量更新集群事件
         *
         * @param event 事件
         */
        protected void onUpdateEvent(final ClusterEvent event) {
            int add = 0;
            //增量更新
            List<ShardEvent> events = event.getDatum();
            //增量更新
            if (events != null) {
                for (ShardEvent e : events) {
                    switch (e.getType()) {
                        case DELETE:
                            onDelete(e.getShard());
                            break;
                        case ADD:
                            add += onAdd(e.getShard()) ? 1 : 0;
                            break;
                    }
                }
            }
            if (add > 0) {
                //新增了节点，重新选举
                candidate();
            }
        }

        /**
         * 关闭所有节点
         *
         * @return 关闭的Future
         */
        protected CompletableFuture<Cluster> closeNodes() {
            final CompletableFuture<Cluster> result = new CompletableFuture<>();
            List<Node> copy = new LinkedList<>(nodes.values());
            final AtomicInteger counter = new AtomicInteger(copy.size());
            for (Node node : copy) {
                node.close().whenComplete((v, e) -> {
                    if (counter.decrementAndGet() == 0) {
                        result.complete(cluster);
                    }
                });
            }
            copy.clear();
            return result;
        }

        protected boolean isOpen() {
            return cluster.stateMachine.isOpen(this);
        }

        /**
         * 判断节点是否存在
         *
         * @param node 节点
         * @return 存在标识
         */
        protected boolean exists(final Node node) {
            return nodes.get(node.getName()) == node;
        }

        /**
         * 重新选举节点
         */
        protected void candidate() {
            //增加选举次数，正在重连的任务会自动放弃
            //冷备节点，已经在算法里面做了最优打散
            backups.clear();
            List<Node> candidates = new LinkedList<>(nodes.values());
            //用最新的参数进行更新
            Candidature.Result result = cluster.candidate(candidates);
            int size = result.getSize();
            if (size > 0) {
                Optional.ofNullable(trigger).ifPresent(o -> o.adjustSemaphore(size));
            }
            /*logger.info(String.format("cluster url:%s, candidate result, candidates:%d, standbys:%d, backups:%d, discards:%d",
                    cluster.url.toString(false, true, "alias", "initTimeout", "region", "datacenter"),
                    result.getCandidates().size(),
                    result.getStandbys().size(),
                    result.getBackups().size(),
                    result.getDiscards().size()
            ));*/
            final AtomicInteger semaphore = new AtomicInteger(result.getCandidates().size());
            //命中节点建立连接
            candidate(result.getCandidates(), (s, n) -> connect(n, r -> semaphore.decrementAndGet()), Node::getWeight);
            //热备节点建立连接
            //TODO 热备节点没有流量，影响自适应评分
            candidate(result.getStandbys(), (s, n) -> connect(n), s -> 0);
            //TODO 平滑关闭，过滤出连接正常的备份节点，候选者每产生一个新的连接，则关闭一个没有流量的备份节点
            candidate(result.getBackups(), (s, n) -> backup(n), Node::getWeight);
            //丢弃的节点
            candidate(result.getDiscards(), (s, n) -> discard(n), null);
            //重置可用节点，因为有些节点可能在这次选举中被放弃了
            readys = new ArrayList<>(connects.values());
        }

        /**
         * 处理选择的分片
         *
         * @param shards   分片
         * @param consumer 消费者
         * @param weighter 权重
         */
        protected void candidate(final List<Node> shards, final BiConsumer<Shard, Node> consumer,
                                 final Function<Node, Integer> weighter) {
            Node node;
            if (shards != null) {
                for (Node shard : shards) {
                    node = nodes.get(shard.getName());
                    if (node != null) {
                        node.setWeight(weighter != null ? weighter.apply(shard) : shard.getWeight());
                        //设置权重
                        consumer.accept(shard, node);
                    }
                }
            }
        }

        /**
         * 丢弃节点
         *
         * @param node 节点
         */
        protected void discard(final Node node) {
            //关闭节点
            node.close();
            //删除连接节点
            connects.remove(node.getName(), node);
        }

        /**
         * 备份节点
         *
         * @param node 节点
         */
        protected void backup(final Node node) {
            //关闭节点
            node.close();
            //删除连接节点
            connects.remove(node.getName(), node);
            //备份节点
            backups.add(new DelayedNode(node));
        }

        /**
         * 打开节点
         *
         * @param node 节点
         */
        protected void connect(final Node node) {
            connect(node, null);
        }

        /**
         * 打开节点
         *
         * @param node     节点
         * @param consumer consumer
         */
        protected void connect(final Node node, final Consumer<Node> consumer) {
            if (!isOpen()) {
                return;
            }
            //把初始化状态改成候选状态
            node.getTransition().tryCandidate();
            //候选者状态进行连接，其它状态要么已经在连接节点里面，或者会触发事件通知
            if (node.getState() == ShardState.CANDIDATE) {
                node.open().whenComplete((v, error) -> {
                    //已经关闭了
                    if (!isOpen() && error == null) {
                        node.close();
                    }
                    //异步处理
                    tasks.offer(() -> onOpen(node, error));
                    if (consumer != null) {
                        consumer.accept(node);
                    }
                });
            }
        }

        /**
         * 节点打开
         *
         * @param node  节点
         * @param error 异常
         */
        protected void onOpen(final Node node, final Throwable error) {
            if (!isOpen()) {
                node.close();
                logger.warn(String.format("Close the unused node instance %s. because the cluster is closed or reopened. ", node.getName()));
            } else if (!exists(node)) {
                //不存在了，或者变更成了其它实例
                node.close();
                logger.info(String.format("Close the unused node instance %s. because it is removed or updated. ", node.getName()));
            } else if (error == null) {
                //这个时候可能节点已经断开了(open的线程和netty断链事件触发的线程不一样)，需要二次检查
                switch (node.getState()) {
                    case CONNECTED:
                    case WEAK:
                        onConnected(node);
                        logger.info(String.format("Success connecting node %s.", node.getName()));
                        break;
                    default:
                        logger.info(String.format("The node %s is not connected. discard this connected event.", node.getName()));
                }
            } else if (error instanceof TransportException) {
                logger.error(String.format("Failed connecting node %s. caused by %s.", node.getName(), toSimpleString(error)));
                onDisconnect(node, cluster.getRetryTime(error));
            } else if (error instanceof ProtocolException) {
                logger.error(String.format("Failed connecting node %s. caused by %s.", node.getName(), toSimpleString(error)));
                onDisconnect(node, cluster.getRetryTime(error));
            } else if (error instanceof AuthenticationException) {
                logger.error(String.format("Failed connecting node %s. caused by %s.", node.getName(), toSimpleString(error)));
                onDisconnect(node, cluster.getRetryTime(error));
            } else {
                logger.error(String.format("Failed connecting node %s. caused by %s.", node.getName(), error.getMessage()), error);
                onDisconnect(node, cluster.getRetryTime(error));
            }

        }

        /**
         * 从备选节点选择一条进行连接
         *
         * @param owner 是否要拥有者
         */
        protected void supply(boolean owner) {
            //判断是否启动了定时任务
            if (isOpen() && supplies.get() > 0
                    && (!owner || supplyOwner.compareAndSet(false, true))) {
                DelayedNode delay;
                while ((delay = backups.poll()) != null) {
                    if (exists(delay.getNode())) {
                        supply(delay.getNode());
                        if (supplies.decrementAndGet() <= 0) {
                            break;
                        }
                    }
                }
                //如果没有备份节点了，则设置待补充节点数为0
                int v = supplies.get();
                if (backups.size() == 0 && v > 0) {
                    supplies.compareAndSet(v, 0);
                }
                if (supplies.get() > 0) {
                    timer().add(supplyTask, SystemClock.now() + 1000, () -> supply(false));
                } else {
                    supplyOwner.set(false);
                    supply(true);
                }
            }
        }

        /**
         * 补充节点
         *
         * @param node 节点
         */
        protected void supply(final Node node) {
            node.getTransition().tryInitial();
            tasks.offer(() -> {
                if (exists(node)) {
                    node.retry.incrementTimes();
                    connect(node);
                }
            });
        }

        /**
         * 节点连接上
         *
         * @param node 节点
         */
        protected void onConnected(final Node node) {
            Node old = connects.put(node.getName(), node);
            if (old == node) {
                //同一个节点
                return;
            } else if (old != null) {
                //不同的节点
                old.close();
            }
            readys = new ArrayList<>(connects.values());
            Optional.ofNullable(trigger).ifPresent(o -> {
                if (!o.acquire()) {
                    //满足足够的连接了
                    trigger = null;
                }
            });
        }

        /**
         * 连接断开
         *
         * @param node      节点
         * @param retryTime 重连时间
         */
        protected void onDisconnect(final Node node, final long retryTime) {
            //由于异步事件触发，有可能该节点已经被重新连接了，需要再次判断状态
            if (node.getState() == ShardState.DISCONNECT) {
                //把它从连接节点里面删除
                if (connects.remove(node.getName(), node)) {
                    readys = new ArrayList<>(connects.values());
                }
                //节点断开，这个时候有可能注册中心事件造成不存在了
                if (exists(node)) {
                    //如果没有下线，则尝试重连
                    node.getRetry().setRetryTime(retryTime);
                    //把当前节点放回到后备节点
                    backups.offer(new DelayedNode(node));
                    //补充新节点
                    supplies.incrementAndGet();
                    supply(true);
                }
            }
        }

        /**
         * 收到服务端下线通知，正在优雅关闭连接中，需要提前从就绪节点列表中删除
         *
         * @param node 节点
         */
        protected void onClosing(final Node node) {
            //事件异步触发，需要再次判断状态
            if (node.getState() == ShardState.DISCONNECT) {
                //用于优雅关闭，节点状态为断开,连接通道还没有断开，把它从连接节点里面删除
                if (connects.remove(node.getName(), node)) {
                    readys = new ArrayList<>(connects.values());
                }
            }
        }

        /**
         * 节点关闭
         *
         * @param node      节点
         * @param retryTime 重试时间
         */
        protected void onClose(final Node node, final long retryTime) {
            //异步关闭结束，需要再次放在同步任务队列中执行
            if (node.getState() == ShardState.DISCONNECT) {
                //这个时候节点状态断开，已经没有请求，可以安全关闭连接
                node.close().whenComplete((v, e) -> tasks.offer(() -> onDisconnect(node, retryTime)));
            }
        }

        /**
         * 删除分片事件
         *
         * @param shard 分片
         */
        protected void onDelete(final Shard shard) {
            String name = shard.getName();
            Node node = nodes.remove(name);
            if (node != null) {
                logger.info(String.format("delete shard %s", shard.getName()));
                //由于注册中心的事件晚于服务端直接发送的下线命令，所以这里可以做到优雅关闭节点
                node.close();
                if (connects.remove(name) != null) {
                    //重新设置就绪节点
                    readys = new ArrayList<>(connects.values());
                    supplies.incrementAndGet();
                    //从备选节点中重新创建连接
                    supply(true);
                }
            }
        }

        /**
         * 添加分片事件
         *
         * @param shard 分片
         */
        protected boolean onAdd(final Shard shard) {
            URL url = shard.getUrl();
            if (url == null) {
                //节点没有地址，则删除掉
                return false;
            }
            //创建节点
            Node node = cluster.createNode(shard, nodeHandler);
            //过滤掉无效的节点
            for (NodeFilter filter : NODE_FILTER.extensions()) {
                if (filter.filter(cluster, node)) {
                    logger.info(String.format("discard shard %s(region=%s,dataCenter=%s,protocol=%s,version=%s,weight=%d) for cluster %s",
                            shard.getName(), shard.getRegion(), shard.getDataCenter(), shard.getProtocol(),
                            shard.getUrl().getString("version", ""), shard.getWeight(), cluster.name));
                    onDelete(shard);
                    return false;
                }
            }
            //比较是否发生变化
            Node previous = nodes.get(shard.getName());
            if (!cluster.isChanged(shard, previous)) {
                return false;
            }
            if (logger.isInfoEnabled()) {
                logger.info(String.format("add shard %s(region=%s,dataCenter=%s,protocol=%s,version=%s,weight=%d) for cluster %s",
                        shard.getName(), shard.getRegion(), shard.getDataCenter(), shard.getProtocol(),
                        shard.getUrl().getString("version", ""), shard.getWeight(), cluster.name));
            }
            //新增节点都进行覆盖，防止分片数量发生了变化
            previous = nodes.put(shard.getName(), node);
            if (previous != null) {
                //确保前置节点关闭，防止并发open，报连接关闭异常
                CompletableFuture<Void> waiting = new CompletableFuture<>();
                previous.close().whenComplete((v, e) -> waiting.complete(null));
                node.setPrecondition(waiting);
            }
            //新增节点初始化状态
            node.getTransition().tryInitial();
            return true;
        }
    }

    /**
     * 延迟连接节点
     */
    protected static class DelayedNode implements Delayed {
        /**
         * 节点
         */
        protected Node node;

        /**
         * 构造函数
         *
         * @param node 节点
         */
        public DelayedNode(Node node) {
            this.node = node;
        }

        public Node getNode() {
            return node;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long duration = node.getRetryTime() - SystemClock.now();
            duration = duration < 0 ? 0 : duration;
            return unit.convert(duration, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long result = node.getRetryTime() - ((DelayedNode) o).node.getRetryTime();
            if (result > 0) {
                return 1;
            } else if (result < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 触发器
     */
    protected static class Trigger {

        /**
         * cluster 名称
         */
        protected String clusterName;
        /**
         * 通知触发的信号量
         */
        protected AtomicLong semaphore;
        /**
         * 初始化连接数
         */
        protected volatile int initSize;
        /**
         * 超时时间
         */
        protected long timeout;
        /**
         * 建连超时时间
         */
        protected long connectTimeout;
        /**
         * 是否验证初始化建连成功
         */
        protected Supplier<Boolean> check;
        /**
         * 就绪处理器
         */
        protected Runnable ready;
        /**
         * 超时处理器
         */
        protected Runnable whenTimeout;
        /**
         * 是否通知了等到初始化的消费者，避免超时检测和连接通知并发触发
         */
        protected AtomicBoolean mail = new AtomicBoolean();
        /**
         * 第一次选择
         */
        protected AtomicBoolean first = new AtomicBoolean(false);
        /**
         * 第一次收到cluster事件
         */
        protected AtomicBoolean firstConnect = new AtomicBoolean(false);

        /**
         * 构造函数
         *
         * @param clusterName 名称
         * @param initSize    信号量
         * @param timeout     超时时间
         * @param ready       就绪处理器
         * @param whenTimeout 超时处理器
         */
        public Trigger(final String clusterName, final int initSize, final long timeout, final long connectTimeout,
                       Supplier<Boolean> check, final Runnable ready, final Runnable whenTimeout) {
            this.clusterName = clusterName;
            this.initSize = initSize;
            this.semaphore = new AtomicLong(initSize);
            this.timeout = timeout;
            this.connectTimeout = connectTimeout;
            this.check = check;
            this.ready = ready;
            this.whenTimeout = whenTimeout;
            if (timeout > 0) {
                //超时检测
                timer().add("TimeoutTask-" + clusterName, SystemClock.now() + timeout, this::doTimeout);
            }
        }

        /**
         * 调整触发计数器，只能调整一次
         *
         * @param size 尺寸
         */
        public boolean adjustSemaphore(final int size) {
            if (size <= 0) {
                return false;
            }
            if (first.compareAndSet(false, true)) {
                //第一次选择结果，对参数进行修正，避免initSize设置的不合理
                long value = Math.max(1, Math.min(size * 2 / 3, semaphore.get()));
                semaphore.set(value);
                //初始化连接数也要重置，否则超时的时候会触发就绪事件
                initSize = (int) value;
                return true;
            }
            return false;
        }

        /**
         * 接收到集群全量事件，触发连接超时检查任务
         *
         * @param size 节点数量
         */
        public void onFull(int size) {
            if (firstConnect.compareAndSet(false, true)) {
                if (size == 0 && !check.get()) {
                    //没有服务提供者，不需要初始化建立连接
                    semaphore.set(0L);
                    fire(ready);
                } else if (connectTimeout > 0) {
                    timer().add("ConnectTimeoutTask-" + clusterName, SystemClock.now() + connectTimeout, this::doTimeout);
                }
            }
        }

        /**
         * 超时
         */
        protected void doTimeout() {
            //只要有一个连接或者不需要连接就触发就绪事件
            fire(semaphore.get() < initSize || !check.get() ? ready : whenTimeout);
        }

        /**
         * 关闭
         */
        public void close() {
            fire(whenTimeout);
        }

        /**
         * 触发
         */
        protected boolean fire(final Runnable runnable) {
            if (mail.compareAndSet(false, true)) {
                runnable.run();
                return true;
            }
            return false;
        }

        /**
         * 获取信号量
         *
         * @return 成功标识
         */
        public boolean acquire() {
            if (semaphore.decrementAndGet() == 0) {
                fire(ready);
                return false;
            }
            return true;
        }

    }

    /**
     * 面板任务
     */
    protected static class DashboardTask implements Timer.TimeTask {
        /**
         * 集群
         */
        protected Cluster cluster;
        /**
         * 面板
         */
        protected final Dashboard dashboard;
        /**
         * 打开的对象
         */
        protected final ClusterController controller;
        /**
         * 时间窗口
         */
        protected final long windowTime;
        /**
         * 下次控制器时间
         */
        protected long time;
        /**
         * 名称
         */
        protected final String name;


        /**
         * 构造函数
         *
         * @param cluster    集群
         * @param controller 控制器
         */
        public DashboardTask(final Cluster cluster, final ClusterController controller) {
            this.cluster = cluster;
            this.dashboard = cluster.dashboard;
            this.controller = controller;
            //把集群指标过期分布到1秒钟以内，避免同时进行控制器
            long lastSnapshotTime = SystemClock.now() + ThreadLocalRandom.current().nextInt(1000);
            this.windowTime = dashboard.getMetric().getWindowTime();
            this.time = lastSnapshotTime + windowTime;
            this.dashboard.setLastSnapshotTime(lastSnapshotTime);
            this.name = this.getClass().getSimpleName() + "-" + cluster.name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public long getTime() {
            return time;
        }

        @Override
        public void run() {
            IntStateMachine<Void, ClusterController> stateMachine = cluster.stateMachine;
            //启动的时候有可能在Opening状态
            if (stateMachine.isOpen(controller)) {
                if (stateMachine.isOpened(controller)) {
                    dashboard.snapshot();
                }
                time = SystemClock.now() + windowTime;
                timer().add(this);
            }
        }
    }

}

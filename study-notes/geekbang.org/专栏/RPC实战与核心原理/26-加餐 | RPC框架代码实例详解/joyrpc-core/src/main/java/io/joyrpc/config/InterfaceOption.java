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

import io.joyrpc.cache.Cache;
import io.joyrpc.cache.CacheKeyGenerator;
import io.joyrpc.cluster.Shard;
import io.joyrpc.cluster.distribution.CircuitBreaker;
import io.joyrpc.cluster.distribution.FailoverPolicy;
import io.joyrpc.cluster.distribution.Router;
import io.joyrpc.cluster.distribution.loadbalance.adaptive.AdaptivePolicy;
import io.joyrpc.context.auth.IPPermission;
import io.joyrpc.context.limiter.LimiterConfiguration.ClassLimiter;
import io.joyrpc.invoker.CallbackMethod;
import io.joyrpc.permission.BlackWhiteList;
import io.joyrpc.protocol.message.Invocation;
import io.joyrpc.protocol.message.RequestMessage;
import io.joyrpc.proxy.MethodCaller;
import io.joyrpc.transaction.TransactionOption;
import io.joyrpc.util.GenericMethod;
import io.joyrpc.util.IDLMethodDesc;

import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * 接口运行时选项，抽取出接口是方便第三方在扩展Filter的时候也可以扩展实现方法选项，用于提前绑定相关参数
 */
public interface InterfaceOption {

    /**
     * 根据方法名称返回选项
     *
     * @param methodName 方法名称
     * @return 选项
     */
    MethodOption getOption(String methodName);

    /**
     * 是否泛化调用
     *
     * @return 泛化调用标识
     */
    boolean isGeneric();

    /**
     * 是否启用假数据
     *
     * @return 启用假数据标识
     */
    boolean isMock();

    /**
     * 是否有回调函数
     *
     * @return 回调函数标识
     */
    boolean isCallback();

    /**
     * 是否启动跟踪
     *
     * @return 启动跟踪标识
     */
    boolean isTrace();

    /**
     * 是否启用缓存
     *
     * @return 启用缓存标识
     */
    boolean isCache();

    /**
     * 是否启用认证
     *
     * @return 启用认证标识
     */
    boolean isValidation();

    /**
     * 是否启用并发数限制
     *
     * @return 启用并发数限制标识
     */
    boolean isConcurrency();

    /**
     * 是否启用限流
     *
     * @return 启用限流标识
     */
    boolean isLimiter();

    /**
     * 是否启用方法黑白名单
     *
     * @return 启用方法黑白名单标识
     */
    default boolean isMethodBlackWhiteList() {
        return false;
    }

    /**
     * 关闭，释放资源，例如移除监听器
     */
    default void close() {
    }

    /**
     * 方法选项
     */
    interface MethodOption {

        /**
         * 获取方法
         *
         * @return 方法
         */
        Method getMethod();

        /**
         * 获取泛型信息
         *
         * @return 泛型方法
         */
        GenericMethod getGenericMethod();

        /**
         * 方法级别隐式传参，合并了接口的隐藏参数
         *
         * @return 合并了接口的方法级隐式传参数
         */
        Map<String, ?> getImplicits();

        /**
         * 获取超时时间
         *
         * @return 超时时间
         */
        int getTimeout();

        /**
         * 并发配置
         *
         * @return 并发配置
         */
        Concurrency getConcurrency();

        /**
         * 缓存策略
         *
         * @return 缓存策略
         */
        CachePolicy getCachePolicy();

        /**
         * 参数验证
         *
         * @return 参数验证
         */
        Validator getValidator();

        /**
         * 令牌
         *
         * @return 令牌
         */
        String getToken();

        /**
         * 获取回调方法信息
         *
         * @return 回调方法
         */
        CallbackMethod getCallback();

        /**
         * 获取事务选项
         *
         * @return 事务选项
         */
        TransactionOption getTransactionOption();

        /**
         * 是否异步调用
         *
         * @return 异步调用标识
         */
        boolean isAsync();

        /**
         * 获取参数信息
         *
         * @return 参数信息
         */
        ArgType getArgType();

        /**
         * 获取参数描述信息
         *
         * @return 参数描述信息
         */
        String getDescription();

        /**
         * 是否开启跟踪
         *
         * @return 开启跟踪标识
         */
        boolean isTrace();

        /**
         * 获取跟踪的span名称
         *
         * @param invocation 调用
         * @return
         */
        String getTraceSpanId(Invocation invocation);
    }

    /**
     * 消费者方法选项
     */
    interface ConsumerMethodOption extends MethodOption {
        /**
         * 获取并行度
         *
         * @return 并行度
         */
        int getForks();

        /**
         * 节点选择器算法
         *
         * @return 节点选择器算法
         */
        BiPredicate<Shard, RequestMessage<Invocation>> getSelector();

        /**
         * 获取分发策略
         *
         * @return 分发策略
         */
        Router getRouter();

        /**
         * 故障切换策略
         *
         * @return 故障切换策略
         */
        FailoverPolicy getFailoverPolicy();

        /**
         * 获取自适应负载均衡配置
         *
         * @return 自适应负载均衡配置
         */
        AdaptivePolicy getAdaptivePolicy();

        /**
         * 获取熔断器
         *
         * @return
         */
        CircuitBreaker getCircuitBreaker();

        /**
         * 返回方法的Mock数据
         *
         * @return 方法的Mock数据
         */
        Map<String, Object> getMock();

        /**
         * 设置是否开启自动计算方法指标阈值
         *
         * @param autoScore 自动计算方法指标阈值标识
         */
        void setAutoScore(boolean autoScore);

    }

    /**
     * 服务提供者方法选项
     */
    interface ProviderMethodOption extends MethodOption {

        /**
         * 方法黑白名单
         *
         * @return 方法黑白名单
         */
        BlackWhiteList<String> getMethodBlackWhiteList();

        /**
         * 获取IP限制
         *
         * @return IP限制
         */
        IPPermission getIPPermission();

        /**
         * 获取限流配置
         *
         * @return 限流配置
         */
        ClassLimiter getLimiter();

        /**
         * 获取动态方法
         *
         * @return 动态方法
         */
        MethodCaller getCaller();

    }

    /**
     * 并发数指标
     */
    class Concurrency {

        /**
         * 最大并发数
         */
        protected int max;

        /**
         * 活动并发
         */
        protected AtomicLong actives = new AtomicLong();

        public Concurrency(int max) {
            this.max = max;
        }

        public int getMax() {
            return max;
        }

        /**
         * 当前并发数
         *
         * @return 并发数
         */
        public long getActives() {
            return actives.get();
        }

        /**
         * 增加
         */
        public void add() {
            actives.incrementAndGet();
        }

        /**
         * 减少并发数
         */
        public void decrement() {
            actives.decrementAndGet();
        }

        /**
         * 唤醒
         */
        public void wakeup() {
            synchronized (this) {
                // 调用结束 通知等待的人
                notifyAll();
            }
        }

        /**
         * 等到
         *
         * @param time 时间
         * @return 成功标识
         */
        public boolean await(final long time) {
            if (time <= 0) {
                return true;
            }
            synchronized (this) {
                try {
                    // 等待执行
                    wait(time);
                    return true;
                } catch (InterruptedException e) {
                    return false;
                }
            }
        }

    }

    /**
     * 缓存策略
     */
    class CachePolicy {
        /**
         * 缓存接口
         */
        protected final Cache<Object, Object> cache;
        /**
         * 缓存键生成器
         */
        protected final CacheKeyGenerator generator;

        public CachePolicy(Cache<Object, Object> cache, CacheKeyGenerator generator) {
            this.cache = cache;
            this.generator = generator;
        }

        public Cache<Object, Object> getCache() {
            return cache;
        }

        public CacheKeyGenerator getGenerator() {
            return generator;
        }
    }

    /**
     * 参数类型
     */
    class ArgType {
        /**
         * 参数类
         */
        protected Class[] classes;
        /**
         * 参数类名
         */
        protected String[] types;
        /**
         * GrpcType提供者
         */
        protected Supplier<IDLMethodDesc> supplier;

        public ArgType(Class[] classes, String[] types, Supplier<IDLMethodDesc> supplier) {
            this.classes = classes;
            this.types = types;
            this.supplier = supplier;
        }

        public Class[] getClasses() {
            return classes;
        }

        public String[] getTypes() {
            return types;
        }

        public IDLMethodDesc getIDLMethodDesc() {
            return supplier.get();
        }
    }

}

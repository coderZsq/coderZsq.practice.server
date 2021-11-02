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

import io.joyrpc.config.validator.ValidatePlugin;
import io.joyrpc.constants.Constants;
import io.joyrpc.invoker.GroupInvoker;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.joyrpc.Plugin.GROUP_ROUTE;
import static io.joyrpc.constants.Constants.DEFAULT_GROUP_ROUTER;

/**
 * 消费组配置
 */
public class ConsumerGroupConfig<T> extends AbstractConsumerConfig<T> implements Serializable {

    /**
     * 目标参数（机房/分组）索引，第一个参数从0开始
     */
    protected Integer dstParam;
    /**
     * 分组路由插件配置
     */
    @ValidatePlugin(extensible = GroupInvoker.class, name = "GROUP_ROUTE", defaultValue = DEFAULT_GROUP_ROUTER)
    protected String groupRouter;

    /**
     * 分组下调用者配置
     */
    protected Map<String, ConsumerConfig<T>> consumerConfigs;

    public Integer getDstParam() {
        return dstParam;
    }

    public void setDstParam(Integer dstParam) {
        this.dstParam = dstParam;
    }

    public String getGroupRouter() {
        return groupRouter;
    }

    public void setGroupRouter(String groupRouter) {
        this.groupRouter = groupRouter;
    }

    public Map<String, ConsumerConfig<T>> getConsumerConfigs() {
        return consumerConfigs;
    }

    public void setConsumerConfigs(Map<String, ConsumerConfig<T>> consumerConfigs) {
        this.consumerConfigs = consumerConfigs;
    }

    @Override
    protected Map<String, String> addAttribute2Map(final Map<String, String> params) {
        super.addAttribute2Map(params);
        addElement2Map(params, Constants.DST_PARAM_OPTION, dstParam);
        // 注册的时候标记属于分组调用的group
        addElement2Map(params, Constants.FROM_GROUP_OPTION, Boolean.TRUE);
        return params;
    }

    @Override
    protected ConsumerPilot create() {
        return new ConsumerGroupPilot<>(this);
    }

    /**
     * 创建分组配置
     *
     * @param alias 分组
     * @return 消费者配置
     */
    protected ConsumerConfig<T> createGroupConfig(final String alias) {
        ConsumerConfig<T> result = new ConsumerConfig<>(this, alias);
        ConsumerConfig config = consumerConfigs == null || consumerConfigs.isEmpty() ? null : consumerConfigs.get(alias);
        if (config != null) {
            Map<String, String> params = config.addAttribute2Map();
            //过滤掉分组不能单独设置的参数
            params.remove(Constants.SERVICE_NAME_OPTION.getName());
            params.remove(Constants.INTERFACE_CLAZZ_OPTION.getName());
            params.remove(Constants.REGISTER_OPTION.getName());
            params.remove(Constants.SUBSCRIBE_OPTION.getName());
            params.remove(Constants.VALIDATION_OPTION.getName());
            params.remove(Constants.GENERIC_OPTION.getName());
            params.remove(Constants.CHECK_OPTION.getName());
            params.remove(Constants.CHANNEL_FACTORY_OPTION.getName());
            params.remove(Constants.TIMESTAMP_KEY);
            params.forEach(result::setParameter);
        }
        return result;
    }

    /**
     * 消费者控制器
     */
    public static class ConsumerGroupPilot<T> extends AbstractConsumerPilot<T, ConsumerGroupConfig<T>> {
        /**
         * 分组调用
         */
        protected transient GroupInvoker route;

        public ConsumerGroupPilot(ConsumerGroupConfig<T> config) {
            super(config);
        }

        @Override
        protected CompletableFuture<Void> doOpen() {
            CompletableFuture<Void> future = new CompletableFuture<>();
            route = GROUP_ROUTE.get(config.groupRouter, Constants.GROUP_ROUTER_OPTION.getValue());
            route.setUrl(serviceUrl);
            route.setAlias(config.alias);
            route.setClass(config.getProxyClass());
            route.setClassName(config.interfaceClazz);
            route.setConfigFunction(config::createGroupConfig);
            route.setup();
            //创建桩
            invocationHandler = new ConsumerInvocationHandler(route, config.getProxyClass(), serviceUrl);
            latch.countDown();
            config.proxy();
            //创建消费者
            route.refer().whenComplete((v, t) -> {
                if (t == null) {
                    future.complete(null);
                } else {
                    future.completeExceptionally(t);
                }
            });
            return future;
        }

        @Override
        public CompletableFuture<Void> close(boolean gracefully) {
            CompletableFuture<Void> future = new CompletableFuture<>();
            invocationHandler = null;
            latch = null;
            if (route != null) {
                route.close().whenComplete((v, t) -> future.complete(null));
            } else {
                future.complete(null);
            }
            return future;
        }

    }

}

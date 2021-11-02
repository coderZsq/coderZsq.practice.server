package io.joyrpc.spring;

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

import io.joyrpc.annotation.Alias;
import io.joyrpc.config.ConsumerConfig;
import io.joyrpc.config.ParameterConfig;
import io.joyrpc.spring.annotation.Spring;
import io.joyrpc.util.StringUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 消费者
 */
public class ConsumerBean<T> extends ConsumerConfig<T> implements InitializingBean, FactoryBean,
        ApplicationContextAware, DisposableBean, BeanNameAware, ApplicationListener {

    /**
     * 参数配置
     */
    protected List<ParameterConfig> params;

    /**
     * spring处理器
     */
    protected transient ConsumerSpring<T> spring;

    /**
     * 默认构造函数，不允许从外部new
     */
    public ConsumerBean() {
        spring = new ConsumerSpring<>(this);
    }

    @Override
    @Spring
    public void setBeanName(String name) {
        spring.setBeanName(name);
    }

    @Override
    @Spring
    public void setApplicationContext(ApplicationContext context) {
        spring.setApplicationContext(context);
    }

    @Override
    public T getObject() throws ExecutionException, InterruptedException {
        return spring.getObject();
    }

    @Override
    public Class getObjectType() {
        return spring.getObjectType();
    }

    @Override
    public boolean isSingleton() {
        return spring.isSingleton();
    }

    @Override
    public void destroy() {
        spring.destroy();
    }

    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        spring.onApplicationEvent(event);
    }

    @Override
    public void afterPropertiesSet() {
        spring.afterPropertiesSet();
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return id;
    }

    public void setName(String name) {
        this.id = name;
    }

    public String getRegistryName() {
        return spring.getRegistryName();
    }

    @Alias("registry")
    public void setRegistryName(String registryName) {
        spring.setRegistryName(registryName);
    }

    public String getConfigureName() {
        return spring.getConfigureName();
    }

    @Alias("configure")
    public void setConfigureName(String configureName) {
        spring.setConfigureName(configureName);
    }

    public List<ParameterConfig> getParams() {
        return params;
    }

    public void setParams(List<ParameterConfig> params) {
        this.params = params;
        if (params != null) {
            params.forEach(param -> {
                if (param != null
                        && StringUtils.isNotEmpty(param.getKey())
                        && StringUtils.isNotEmpty(param.getValue())) {
                    String key = param.isHide() && !param.getKey().startsWith(".") ? "." + param.getKey() : param.getKey();
                    setParameter(key, param.getValue());
                }
            });
        }
    }

}

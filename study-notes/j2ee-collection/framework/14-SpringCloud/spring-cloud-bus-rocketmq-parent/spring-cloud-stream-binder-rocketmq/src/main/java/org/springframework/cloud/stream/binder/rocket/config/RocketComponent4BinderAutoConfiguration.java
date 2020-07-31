/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.binder.rocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.config.RocketMQAutoConfiguration;
import org.apache.rocketmq.spring.config.RocketMQConfigUtils;
import org.apache.rocketmq.spring.config.RocketMQTransactionAnnotationProcessor;
import org.apache.rocketmq.spring.config.TransactionHandlerRegistry;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.binder.rocket.RocketBinderConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@AutoConfigureAfter(RocketMQAutoConfiguration.class)
@ConditionalOnMissingBean(DefaultMQProducer.class)
public class RocketComponent4BinderAutoConfiguration {

    private final Environment environment;

    public RocketComponent4BinderAutoConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = RocketMQConfigUtils.ROCKETMQ_TRANSACTION_ANNOTATION_PROCESSOR_BEAN_NAME)
    @ConditionalOnBean(TransactionHandlerRegistry.class)
    public static RocketMQTransactionAnnotationProcessor transactionAnnotationProcessor(
            TransactionHandlerRegistry transactionHandlerRegistry) {
        return new RocketMQTransactionAnnotationProcessor(transactionHandlerRegistry);
    }


    @Bean
    @ConditionalOnMissingBean(DefaultMQProducer.class)
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer;
        String configNameServer = environment.resolveRequiredPlaceholders(
                "${spring.cloud.stream.rocket.binder.name-server:${rocket.producer.name-server:}}");
        producer = new DefaultMQProducer(RocketBinderConstants.DEFAULT_GROUP);
        producer.setNamesrvAddr(configNameServer);
        return producer;
    }

    @Bean(destroyMethod = "destroy")
    @ConditionalOnMissingBean
    public RocketMQTemplate rocketMQTemplate(DefaultMQProducer mqProducer,
                                             ObjectMapper objectMapper) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(mqProducer);
        rocketMQTemplate.setObjectMapper(objectMapper);
        return rocketMQTemplate;
    }

    @Bean
    @ConditionalOnBean(RocketMQTemplate.class)
    @ConditionalOnMissingBean(TransactionHandlerRegistry.class)
    public TransactionHandlerRegistry transactionHandlerRegistry(
            RocketMQTemplate template) {
        return new TransactionHandlerRegistry(template);
    }

}

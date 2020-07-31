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

import org.apache.rocketmq.spring.config.RocketMQAutoConfiguration;
import org.apache.rocketmq.spring.config.RocketMQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.rocket.RocketMessageChannelBinder;
import org.springframework.cloud.stream.binder.rocket.properties.RocketBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.rocket.properties.RocketExtendedBindingProperties;
import org.springframework.cloud.stream.binder.rocket.provisioning.RocketTopicProvisioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RocketMQAutoConfiguration.class})
@EnableConfigurationProperties({RocketBinderConfigurationProperties.class,
        RocketExtendedBindingProperties.class})
public class RocketBinderAutoConfiguration {

    private final RocketExtendedBindingProperties extendedBindingProperties;
    private final RocketBinderConfigurationProperties rocketBinderConfigurationProperties;
    @Autowired(required = false)
    private RocketMQProperties rocketMQProperties = new RocketMQProperties();

    @Autowired
    public RocketBinderAutoConfiguration(
            RocketExtendedBindingProperties extendedBindingProperties,
            RocketBinderConfigurationProperties rocketBinderConfigurationProperties) {
        this.extendedBindingProperties = extendedBindingProperties;
        this.rocketBinderConfigurationProperties = rocketBinderConfigurationProperties;
    }

    @Bean
    public RocketTopicProvisioner provisioningProvider() {
        return new RocketTopicProvisioner();
    }

    @Bean
    public RocketMessageChannelBinder rocketMessageChannelBinder(
            RocketTopicProvisioner provisioningProvider) {
        RocketMessageChannelBinder binder = new RocketMessageChannelBinder(
                provisioningProvider, extendedBindingProperties,
                rocketBinderConfigurationProperties, rocketMQProperties);
        binder.setExtendedBindingProperties(extendedBindingProperties);
        return binder;
    }

}

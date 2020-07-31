/*
 * Copyright 2016-2017 the original author or authors.
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

package org.springframework.cloud.stream.binder.rocket.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.stream.binder.ExtendedBindingProperties;

import java.util.HashMap;
import java.util.Map;


@ConfigurationProperties("spring.cloud.stream.rocket")
public class RocketExtendedBindingProperties implements ExtendedBindingProperties<RocketConsumerProperties, RocketProducerProperties> {

    private Map<String, RocketBindingProperties> bindings = new HashMap<>();

    public Map<String, RocketBindingProperties> getBindings() {
        return bindings;
    }

    public void setBindings(Map<String, RocketBindingProperties> bindings) {
        this.bindings = bindings;
    }

    @Override
    public synchronized RocketConsumerProperties getExtendedConsumerProperties(String channelName) {
        RocketConsumerProperties properties;
        if (bindings.containsKey(channelName)) {
            if (bindings.get(channelName).getConsumer() != null) {
                properties = bindings.get(channelName).getConsumer();
            } else {
                properties = new RocketConsumerProperties();
                this.bindings.get(channelName).setConsumer(properties);
            }
        } else {
            properties = new RocketConsumerProperties();
            RocketBindingProperties rbp = new RocketBindingProperties();
            rbp.setConsumer(properties);
            bindings.put(channelName, rbp);
        }
        return properties;
    }

    @Override
    public synchronized RocketProducerProperties getExtendedProducerProperties(String channelName) {
        RocketProducerProperties properties;
        if (bindings.containsKey(channelName)) {
            if (bindings.get(channelName).getProducer() != null) {
                properties = bindings.get(channelName).getProducer();
            } else {
                properties = new RocketProducerProperties();
                this.bindings.get(channelName).setProducer(properties);
            }
        } else {
            properties = new RocketProducerProperties();
            RocketBindingProperties rbp = new RocketBindingProperties();
            rbp.setProducer(properties);
            bindings.put(channelName, rbp);
        }
        return properties;
    }

}

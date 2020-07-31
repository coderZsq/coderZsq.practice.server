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

package org.springframework.cloud.stream.binder.rocket.provisioning;

import org.apache.rocketmq.client.Validators;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.binder.rocket.properties.RocketConsumerProperties;
import org.springframework.cloud.stream.binder.rocket.properties.RocketProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.cloud.stream.provisioning.ProvisioningException;
import org.springframework.cloud.stream.provisioning.ProvisioningProvider;


public class RocketTopicProvisioner implements
        ProvisioningProvider<ExtendedConsumerProperties<RocketConsumerProperties>,
                ExtendedProducerProperties<RocketProducerProperties>> {

    @Override
    public ProducerDestination provisionProducerDestination(String name,
                                                            ExtendedProducerProperties<RocketProducerProperties> properties)
            throws ProvisioningException {
        checkTopic(name);
        return new RocketProducerDestination(name);
    }

    @Override
    public ConsumerDestination provisionConsumerDestination(String name, String group,
                                                            ExtendedConsumerProperties<RocketConsumerProperties> properties)
            throws ProvisioningException {
        checkTopic(name);
        return new RocketConsumerDestination(name);
    }

    private void checkTopic(String topic) {
        try {
            Validators.checkTopic(topic);
        } catch (MQClientException e) {
            throw new AssertionError(e);
        }
    }

    private static final class RocketProducerDestination implements ProducerDestination {

        private final String producerDestinationName;

        RocketProducerDestination(String destinationName) {
            this.producerDestinationName = destinationName;
        }

        @Override
        public String getName() {
            return producerDestinationName;
        }

        @Override
        public String getNameForPartition(int partition) {
            return producerDestinationName;
        }

    }

    private static final class RocketConsumerDestination implements ConsumerDestination {

        private final String consumerDestinationName;

        RocketConsumerDestination(String consumerDestinationName) {
            this.consumerDestinationName = consumerDestinationName;
        }

        @Override
        public String getName() {
            return this.consumerDestinationName;
        }

    }

}

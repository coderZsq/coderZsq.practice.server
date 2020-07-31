/*
 * Copyright 2013-2018 the original author or authors.
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

package org.springframework.cloud.stream.binder.rocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.config.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.cloud.stream.binder.AbstractMessageChannelBinder;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.binder.ExtendedPropertiesBinder;
import org.springframework.cloud.stream.binder.rocket.consuming.RocketListenerBindingContainer;
import org.springframework.cloud.stream.binder.rocket.integration.RocketMQInboundChannelAdapter;
import org.springframework.cloud.stream.binder.rocket.integration.RocketMQMessageHandler;
import org.springframework.cloud.stream.binder.rocket.properties.RocketBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.rocket.properties.RocketConsumerProperties;
import org.springframework.cloud.stream.binder.rocket.properties.RocketExtendedBindingProperties;
import org.springframework.cloud.stream.binder.rocket.properties.RocketProducerProperties;
import org.springframework.cloud.stream.binder.rocket.provisioning.RocketTopicProvisioner;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RocketMessageChannelBinder
        extends AbstractMessageChannelBinder<ExtendedConsumerProperties<RocketConsumerProperties>,
        ExtendedProducerProperties<RocketProducerProperties>, RocketTopicProvisioner>
        implements ExtendedPropertiesBinder<MessageChannel, RocketConsumerProperties, RocketProducerProperties>,
        DisposableBean {

    private final RocketBinderConfigurationProperties rocketBinderConfigurationProperties;
    private final RocketMQProperties rocketMQProperties;
    private RocketExtendedBindingProperties extendedBindingProperties = new RocketExtendedBindingProperties();
    private Map<String, String> topicInUse = new HashMap<>();

    public RocketMessageChannelBinder(RocketTopicProvisioner provisioningProvider,
                                      RocketExtendedBindingProperties extendedBindingProperties,
                                      RocketBinderConfigurationProperties rocketBinderConfigurationProperties,
                                      RocketMQProperties rocketMQProperties) {
        super(null, provisioningProvider);
        this.extendedBindingProperties = extendedBindingProperties;
        this.rocketBinderConfigurationProperties = rocketBinderConfigurationProperties;
        this.rocketMQProperties = rocketMQProperties;
    }

    @Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination,
                                                          ExtendedProducerProperties<RocketProducerProperties> producerProperties,
                                                          MessageChannel errorChannel) throws Exception {
        if (producerProperties.getExtension().getEnabled()) {

            // if producerGroup is empty, using destination
            String extendedProducerGroup = producerProperties.getExtension().getGroup();
            String producerGroup = StringUtils.isEmpty(extendedProducerGroup)
                    ? destination.getName()
                    : extendedProducerGroup;

            RocketBinderConfigurationProperties mergedProperties = RocketBinderUtils
                    .mergeProperties(rocketBinderConfigurationProperties,
                            rocketMQProperties);

            RocketMQTemplate rocketMQTemplate;
            if (producerProperties.getExtension().getTransactional()) {
                Map<String, RocketMQTemplate> rocketMQTemplates = getBeanFactory()
                        .getBeansOfType(RocketMQTemplate.class);
                if (rocketMQTemplates.size() == 0) {
                    throw new IllegalStateException(
                            "there is no RocketMQTemplate in Spring BeanFactory");
                } else if (rocketMQTemplates.size() > 1) {
                    throw new IllegalStateException(
                            "there is more than 1 RocketMQTemplates in Spring BeanFactory");
                }
                rocketMQTemplate = rocketMQTemplates.values().iterator().next();
            } else {
                rocketMQTemplate = new RocketMQTemplate();
                rocketMQTemplate.setObjectMapper(this.getApplicationContext()
                        .getBeansOfType(ObjectMapper.class).values().iterator().next());
                DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
                producer.setNamesrvAddr(mergedProperties.getNameServer());
                producer.setSendMsgTimeout(
                        producerProperties.getExtension().getSendMessageTimeout());
                producer.setRetryTimesWhenSendFailed(
                        producerProperties.getExtension().getRetryTimesWhenSendFailed());
                producer.setRetryTimesWhenSendAsyncFailed(producerProperties
                        .getExtension().getRetryTimesWhenSendAsyncFailed());
                producer.setCompressMsgBodyOverHowmuch(producerProperties.getExtension()
                        .getCompressMessageBodyThreshold());
                producer.setRetryAnotherBrokerWhenNotStoreOK(
                        producerProperties.getExtension().isRetryNextServer());
                producer.setMaxMessageSize(
                        producerProperties.getExtension().getMaxMessageSize());
                rocketMQTemplate.setProducer(producer);
            }
            RocketMQMessageHandler messageHandler = new RocketMQMessageHandler(
                    rocketMQTemplate, destination.getName(), producerGroup,
                    producerProperties.getExtension().getTransactional());
            messageHandler.setBeanFactory(this.getApplicationContext().getBeanFactory());
            messageHandler.setSync(producerProperties.getExtension().getSync());

            if (errorChannel != null) {
                messageHandler.setSendFailureChannel(errorChannel);
            }
            return messageHandler;
        } else {
            throw new RuntimeException("Binding for channel " + destination.getName()
                    + " has been disabled, message can't be delivered");
        }
    }

    @Override
    protected MessageProducer createConsumerEndpoint(ConsumerDestination destination,
                                                     String group,
                                                     ExtendedConsumerProperties<RocketConsumerProperties> consumerProperties)
            throws Exception {
        if (group == null || "".equals(group)) {
            throw new RuntimeException(
                    "'group must be configured for channel " + destination.getName());
        }

        RocketListenerBindingContainer listenerContainer = new RocketListenerBindingContainer(
                consumerProperties, rocketBinderConfigurationProperties, this);
        listenerContainer.setConsumerGroup(group);
        listenerContainer.setTopic(destination.getName());
        listenerContainer.setConsumeThreadMax(consumerProperties.getConcurrency());
        listenerContainer.setSuspendCurrentQueueTimeMillis(
                consumerProperties.getExtension().getSuspendCurrentQueueTimeMillis());
        listenerContainer.setDelayLevelWhenNextConsume(
                consumerProperties.getExtension().getDelayLevelWhenNextConsume());
        listenerContainer
                .setNameServer(rocketBinderConfigurationProperties.getNameServer());

        RocketMQInboundChannelAdapter rocketInboundChannelAdapter = new RocketMQInboundChannelAdapter(
                listenerContainer, consumerProperties);

        topicInUse.put(destination.getName(), group);

        ErrorInfrastructure errorInfrastructure = registerErrorInfrastructure(destination,
                group, consumerProperties);
        if (consumerProperties.getMaxAttempts() > 1) {
            rocketInboundChannelAdapter
                    .setRetryTemplate(buildRetryTemplate(consumerProperties));
            rocketInboundChannelAdapter
                    .setRecoveryCallback(errorInfrastructure.getRecoverer());
        } else {
            rocketInboundChannelAdapter
                    .setErrorChannel(errorInfrastructure.getErrorChannel());
        }

        return rocketInboundChannelAdapter;
    }

    @Override
    public RocketConsumerProperties getExtendedConsumerProperties(String channelName) {
        return extendedBindingProperties.getExtendedConsumerProperties(channelName);
    }

    @Override
    public RocketProducerProperties getExtendedProducerProperties(String channelName) {
        return extendedBindingProperties.getExtendedProducerProperties(channelName);
    }

    public Map<String, String> getTopicInUse() {
        return topicInUse;
    }

/*    @Override
    public String getDefaultsPrefix() {
        return extendedBindingProperties.getDefaultsPrefix();
    }

    @Override
    public Class<? extends BinderSpecificPropertiesProvider> getExtendedPropertiesEntryClass() {
        return extendedBindingProperties.getExtendedPropertiesEntryClass();
    }*/

    public void setExtendedBindingProperties(
            RocketExtendedBindingProperties extendedBindingProperties) {
        this.extendedBindingProperties = extendedBindingProperties;
    }

    @Override
    public void destroy() throws Exception {

    }
}

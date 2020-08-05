package com.sq.springcloudstreambinderrocketmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQMessageChannelBinderConfiguration {
    @Bean
    public RocketMQMessageChannelBinder rocketMQMessageChannelBinder() {
        return new RocketMQMessageChannelBinder();
    }
}

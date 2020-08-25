package com.seemygo.shop.cloud.alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfig {
    @Autowired
    private AlipayProperties properties;

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(properties.getGatewayUrl(), properties.getAppId(), properties.getMerchantPrivateKey(), "json", properties.getCharset(), properties.getAlipayPublicKey(), properties.getSignType());
    }
}

package com.coderZsq.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Hourse hourse() {
        return new Hourse();
    }

    @Bean
    public Money money() {
        return new Money();
    }

    /**
     * 在IoC容器需要判断一下, 如果容器中有Hourse的实例, 就创建lucy, 否则就不创建
     * @return
     */
    @Bean
    @ConditionalBean(Money.class)
    public GirlFriend lucy() {
        return new GirlFriend();
    }
}

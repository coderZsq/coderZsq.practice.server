package com.seemygo.shop.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * 会员服务
 */
@SpringBootApplication
@EnableCircuitBreaker
public class MemberServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberServerApplication.class, args);
    }
}

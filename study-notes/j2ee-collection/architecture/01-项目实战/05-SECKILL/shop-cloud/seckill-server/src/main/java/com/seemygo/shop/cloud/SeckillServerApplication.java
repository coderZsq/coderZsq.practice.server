package com.seemygo.shop.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SeckillServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillServerApplication.class, args);
    }
}

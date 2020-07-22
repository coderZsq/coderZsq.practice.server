package com.sq.orderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// 启用feign远程调用 扫描贴了@FeignClient的接口, 并且创建一个代理对象
@EnableFeignClients(basePackages = "com.sq.productapi.feign")
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

    // @Bean
    // @LoadBalanced // 添加负载均衡算法
    // public RestTemplate restTemplate() {
    //     return new RestTemplate();
    // }
}

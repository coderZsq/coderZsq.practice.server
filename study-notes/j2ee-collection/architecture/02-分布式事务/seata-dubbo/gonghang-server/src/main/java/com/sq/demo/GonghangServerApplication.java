package com.sq.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class GonghangServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GonghangServerApplication.class, args);
    }

}

package com.coderzsq._01_hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication // 自动装配原理
public class AppConfig {

    public static void main(String[] args) {
        // 打印main方法的参数
        System.out.println(Arrays.toString(args));
        // 方式一: 使用SpringApplication的静态方法
        SpringApplication.run(AppConfig.class, args);
        // 方式二: 使用SpringApplication对象的run方法
        // SpringApplication springApplication = new SpringApplication(AppConfig.class);
        // springApplication.run(args);
        // 方式三: 使用一个建造器模式
        // SpringApplicationBuilder builder = new SpringApplicationBuilder(AppConfig.class);
        // builder.run(args);
    }

}

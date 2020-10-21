package com.sq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableAutoConfiguration
// @ComponentScan
public class Application {
    public static void main(String[] args) {
        System.out.println("----------------main");
        SpringApplication.run(Application.class, args);
    }
}

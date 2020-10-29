package com.sq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sq.dao")
public class Application47 {
    public static void main(String[] args) {
        SpringApplication.run(Application47.class, args);
    }
}

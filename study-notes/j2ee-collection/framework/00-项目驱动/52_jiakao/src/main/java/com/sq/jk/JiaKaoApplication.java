package com.sq.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sq.jk.mapper")
public class JiaKaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JiaKaoApplication.class);
    }
}

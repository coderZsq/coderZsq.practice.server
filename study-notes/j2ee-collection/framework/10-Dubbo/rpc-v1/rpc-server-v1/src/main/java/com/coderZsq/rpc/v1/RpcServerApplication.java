package com.coderZsq.rpc.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcServerApplication.class, args);
    }

    @Bean
    public RpcServer rpcServer(ApplicationContext applicationContext) {
        RpcServer rpcServer = new RpcServer(9000, applicationContext);
        return rpcServer;
    }
}

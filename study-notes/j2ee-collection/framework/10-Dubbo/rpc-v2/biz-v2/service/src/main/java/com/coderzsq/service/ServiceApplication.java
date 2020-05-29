package com.coderzsq.service;

import com.coderZsq.rpc.RpcRegistry;
import com.coderZsq.rpc.RpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    public RpcRegistry rpcRegistry() {
        RpcRegistry rpcRegistry = new RpcRegistry("172.16.21.175:2181");
        return rpcRegistry;
    }

    // 启动一下socket服务
    @Bean
    public RpcServer rpcServer(ApplicationContext applicationContext, RpcRegistry rpcRegistry) {
        RpcServer rpcServer = new RpcServer("127.0.0.1", 6000, applicationContext, rpcRegistry);
        return rpcServer;
    }
}

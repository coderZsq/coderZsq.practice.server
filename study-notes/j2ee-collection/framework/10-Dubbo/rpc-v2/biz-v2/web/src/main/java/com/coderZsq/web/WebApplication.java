package com.coderZsq.web;

import com.coderZsq.common.service.IProductService;
import com.coderZsq.rpc.RpcClient;
import com.coderZsq.rpc.RpcDiscover;
import com.coderZsq.rpc.RpcProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public RpcDiscover rpcDiscover() throws Exception {
        RpcDiscover rpcDiscover = new RpcDiscover("172.16.21.175:2181");
        return rpcDiscover;
    }

    @Bean
    public RpcClient rpcClient(RpcDiscover rpcDiscover) {
        RpcClient rpcClient = new RpcClient(rpcDiscover);
        return rpcClient;
    }

    @Bean
    public RpcProxy rpcProxy(RpcClient rpcClient) {
        return new RpcProxy(rpcClient);
    }

    @Bean
    public IProductService productService(RpcProxy rpcProxy) {
        return rpcProxy.getProxy(IProductService.class);
    }
}

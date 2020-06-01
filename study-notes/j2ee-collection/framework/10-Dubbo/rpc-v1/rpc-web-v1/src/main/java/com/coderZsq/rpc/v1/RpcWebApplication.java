package com.coderZsq.rpc.v1;

import com.coderZsq.rpc.v1.service.IProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RpcWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcWebApplication.class, args);
    }

    @Bean
    public RpcClient rpcClient() {
        RpcClient client = new RpcClient("127.0.0.1", 9000);
        return client;
    }

    @Bean
    public RpcProxy rpcProxy(RpcClient rpcClient) {
        return new RpcProxy(rpcClient);
    }

    @Bean
    public IProductService productService(RpcProxy rpcProxy) {
        // 因为本地没有对应的实现类的字节码对象, 无法实例化对象
        // 通过代理对象 调用远程的服务方法
        IProductService productService = rpcProxy.getProxy(IProductService.class);
        return productService;
    }
}

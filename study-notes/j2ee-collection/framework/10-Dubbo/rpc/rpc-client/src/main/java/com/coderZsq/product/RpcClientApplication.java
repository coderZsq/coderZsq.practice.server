package com.coderZsq.product;

import com.coderZsq.product.service.IProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcClientApplication.class, args);
    }

    @Bean
    public RpcProxy rpcProxy() {
        return new RpcProxy();
    }

    @Bean
    public IProductService productService() {
        IProductService productService = rpcProxy().getInstance(IProductService.class);
        return productService;
    }
}

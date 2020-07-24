package com.sq.productapi.feign;

import com.sq.productapi.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 1 使用FeignClient标记为feign接口, 并且name参数的值是提供服务的service-id
 * 2 使用@RequestMapping规范请求的url地址
 * 3 使用@RequestParam("id")确定请求的参数
 */
@FeignClient(name = "product-server", fallback = ProductFeignFallback.class)
public interface ProductFeignApi {
    @RequestMapping("/api/v1/product/find")
    Product find(@RequestParam("id") Long id);
}

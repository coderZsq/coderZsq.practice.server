package com.maoge.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-server")
public interface ProductFeign {
    @RequestMapping("product/reduce")
    public String reduce(@RequestParam("id") Integer id, @RequestParam("num")Integer num);
}

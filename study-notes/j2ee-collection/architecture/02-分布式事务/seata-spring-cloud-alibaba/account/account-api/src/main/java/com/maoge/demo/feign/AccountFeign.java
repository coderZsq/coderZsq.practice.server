package com.maoge.demo.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account-server")
public interface AccountFeign {
    @RequestMapping("account/reduce")
    public String reduce(@RequestParam("id") Integer id, @RequestParam("amount")Integer amount);
}

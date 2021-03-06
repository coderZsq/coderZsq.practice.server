package com.sq.orderserver.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sq.orderserver.domain.Order;
import com.sq.orderserver.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping
    @HystrixCommand(fallbackMethod = "saveFallback")
    public Order save(Long userId, Long productId, HttpServletRequest request) {
        log.info("下订单服务调用");
        // String value = request.getHeader("Cookie");
        // System.out.println("value = " + value);
        return orderService.save(userId, productId);
    }

    public Order saveFallback(Long userId, Long productId, HttpServletRequest request) {
        System.out.println("调用熔断处理方案");
        return new Order();
    }
}

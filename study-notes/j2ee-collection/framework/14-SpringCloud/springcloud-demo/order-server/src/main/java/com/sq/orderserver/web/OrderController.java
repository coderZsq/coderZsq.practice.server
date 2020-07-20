package com.sq.orderserver.web;

import com.sq.orderserver.domain.Order;
import com.sq.orderserver.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping
    public Order save(Long userId, Long productId) {
        return orderService.save(userId, productId);
    }
}

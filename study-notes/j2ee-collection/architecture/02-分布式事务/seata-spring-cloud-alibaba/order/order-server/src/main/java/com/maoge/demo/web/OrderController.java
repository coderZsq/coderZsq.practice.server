package com.maoge.demo.web;


import com.maoge.demo.domain.Order;
import com.maoge.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @RequestMapping("order/create")
    public String createOrder(Order order){
        orderService.createOrder(order);
        return "success";
    }
}

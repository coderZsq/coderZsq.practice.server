package com.coderZsq.web;

import com.coderZsq.domain.Order;
import com.coderZsq.service.IOrderService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("save")
    public Order save(Long userId, Long productId) {
        // 消费者 --> 提供者 传递自定义参数
        RpcContext.getContext().setAttachment("name", "coderZsq");
        return orderService.save(userId, productId);
    }
}

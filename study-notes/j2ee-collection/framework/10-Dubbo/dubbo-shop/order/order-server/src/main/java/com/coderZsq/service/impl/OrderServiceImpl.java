package com.coderZsq.service.impl;

import com.coderZsq.domain.Order;
import com.coderZsq.domain.Product;
import com.coderZsq.service.IOrderService;
import com.coderZsq.service.IProductService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {
    @Reference
    private IProductService productService;

    @Override
    public Order save(Long userId, Long productId) {
        Product product = productService.get(productId); // 远程获取
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        order.setCreateTime(new Date());
        order.setUserId(userId);
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());
        System.out.println("执行保存订单操作");
        return order;
    }
}

package com.sq.orderserver.service.impl;

import com.sq.orderserver.domain.Order;
import com.sq.orderserver.service.IOrderService;
import com.sq.productapi.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order save(Long userId, Long productId) {
        // 商品信息应该通过productId 从商品服务查询到
        // http请求: http://localhost:8081/api/v1/product/find?id=1
        // 方式: httpClient, RestTemplate, URLConnection
        Product product = restTemplate.getForObject("http://PRODUCT-SERVER/api/v1/product/find?id=" + productId, Product.class); // 远程获取
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

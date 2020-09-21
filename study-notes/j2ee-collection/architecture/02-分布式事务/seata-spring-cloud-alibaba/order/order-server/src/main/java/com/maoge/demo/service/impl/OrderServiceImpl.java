package com.maoge.demo.service.impl;

import com.maoge.demo.domain.Order;
import com.maoge.demo.feign.AccountFeign;
import com.maoge.demo.feign.ProductFeign;
import com.maoge.demo.mapper.OrderMapper;
import com.maoge.demo.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;

    private ProductFeign productFeign;

    private AccountFeign accountFeign;
    @Override
    @GlobalTransactional
//    @Transactional
    public void createOrder(Order order) {
        order.setCreateTime(new Date());
        //1 创建订单
        orderMapper.createOrder(order);
        //2 扣减库存
        productFeign.reduce(order.getProductId(),order.getSum());
        //3 扣减账户余额
        accountFeign.reduce(order.getAccountId(),order.getAmount());
//        int i=1/0;
    }
}

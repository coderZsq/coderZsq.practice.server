package com.sq.orderserver.service;

import com.sq.orderserver.domain.Order;

public interface IOrderService {
    public Order save(Long userId, Long productId);
}

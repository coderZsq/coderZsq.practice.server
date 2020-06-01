package com.coderZsq.service;

import com.coderZsq.domain.Order;

public interface IOrderService {
    public Order save(Long userId, Long productId);
}

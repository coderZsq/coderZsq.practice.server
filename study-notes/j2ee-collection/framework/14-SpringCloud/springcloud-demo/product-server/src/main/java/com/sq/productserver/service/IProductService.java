package com.sq.productserver.service;

import com.sq.productapi.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> list();
    Product get(Long id);
}

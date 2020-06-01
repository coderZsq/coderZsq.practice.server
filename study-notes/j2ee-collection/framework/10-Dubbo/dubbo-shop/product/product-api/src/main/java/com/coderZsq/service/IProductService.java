package com.coderZsq.service;

import com.coderZsq.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> list();
    Product get(Long id);
}

package com.sq.esdemo.service;

import com.sq.esdemo.domain.Product;

import java.util.List;

public interface IProductService {
    void save(Product product);
    void update(Product product);
    void delete(String id);
    Product get(String id);
    List<Product> list();
}

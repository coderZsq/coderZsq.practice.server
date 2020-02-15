package com.coderZsq._24_shopping.dao;

import com.coderZsq._24_shopping.domain.Product;

import java.util.List;

public interface IProductDAO {
    void save(Product obj);

    void update(Product obj);

    void delete(Long id);

    Product get(Long id);

    List<Product> list();
}

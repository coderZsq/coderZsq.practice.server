package com.coderZsq._24_shopping.dao;

import com.coderZsq._24_shopping.domain.ProductDir;

import java.util.List;

public interface IProductDirDAO {
    void save(ProductDir obj);

    void update(ProductDir obj);

    void delete(Long id);

    ProductDir get(Long id);

    List<ProductDir> list();
}

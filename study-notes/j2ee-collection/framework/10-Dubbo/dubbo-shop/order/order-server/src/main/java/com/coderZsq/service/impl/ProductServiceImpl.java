package com.coderZsq.service.impl;

import com.coderZsq.domain.Product;
import com.coderZsq.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    @Override
    public List<Product> list() {
        return null;
    }

    @Override
    public Product get(Long id) {
        System.out.println("调用远程服务异常, 使用兜底数据");
        final Product product = new Product();
        product.setPrice(100);
        product.setStock(1);
        product.setName("兜底数据");
        return product;
    }
}

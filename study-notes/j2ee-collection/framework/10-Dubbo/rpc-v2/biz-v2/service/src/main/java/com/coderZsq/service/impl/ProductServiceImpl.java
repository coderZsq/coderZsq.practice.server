package com.coderZsq.service.impl;


import com.coderZsq.common.domain.Product;
import com.coderZsq.common.service.IProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("IProductService")
public class ProductServiceImpl implements IProductService {

    @Override
    public void save(Product product) {
        System.out.println("产品保存成功: " + product);
    }

    @Override
    public void deleteById(Long productId) {
        System.out.println("产品删除成功: " + productId);
    }

    @Override
    public void update(Product product) {
        System.out.println("产品修改成功: " + product);
    }

    @Override
    public Product get(Long productId) {
        System.out.println("产品获取成功");
        return new Product(1L, "001", "笔记本电脑", BigDecimal.TEN);
    }
}

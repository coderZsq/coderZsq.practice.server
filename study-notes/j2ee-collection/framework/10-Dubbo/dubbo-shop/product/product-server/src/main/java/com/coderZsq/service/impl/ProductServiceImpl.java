package com.coderZsq.service.impl;

import com.coderZsq.domain.Product;
import com.coderZsq.service.IProductService;
import com.coderZsq.mapper.ProductMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list() {
        return productMapper.list();
    }

    @Override
    public Product get(Long id) {
        return productMapper.get(id);
    }
}

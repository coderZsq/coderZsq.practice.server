package com.sq.productserver.service.impl;

import com.sq.productapi.domain.Product;
import com.sq.productserver.mapper.ProductMapper;
import com.sq.productserver.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

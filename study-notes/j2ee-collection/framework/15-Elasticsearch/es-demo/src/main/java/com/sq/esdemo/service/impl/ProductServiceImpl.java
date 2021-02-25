package com.sq.esdemo.service.impl;

import com.sq.esdemo.dao.ProductRepository;
import com.sq.esdemo.domain.Product;
import com.sq.esdemo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product get(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> list() {
        List<Product> result = new ArrayList<>();
        final Iterable<Product> products = productRepository.findAll();
        for (Product product: products) {
            result.add(product);
        }
        return result;
    }
}

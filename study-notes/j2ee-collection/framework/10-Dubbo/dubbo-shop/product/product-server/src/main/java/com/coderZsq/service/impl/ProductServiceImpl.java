package com.coderZsq.service.impl;

import com.coderZsq.domain.Product;
import com.coderZsq.service.IProductService;
import com.coderZsq.mapper.ProductMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(loadbalance = "roundrobin", cluster = "failfast")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list() {
        return productMapper.list();
    }

    @Override
    public Product get(Long id) {
        System.out.println("调用指定服务, 正在写的测试代码");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productMapper.get(id);
    }
}

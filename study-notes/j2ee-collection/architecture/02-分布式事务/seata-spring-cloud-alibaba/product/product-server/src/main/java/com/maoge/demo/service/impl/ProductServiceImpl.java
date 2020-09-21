package com.maoge.demo.service.impl;

import com.maoge.demo.mapper.ProductMapper;
import com.maoge.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    public void reduce(Integer id, Integer num){
        productMapper.reduce(id,num);
    }
}

package com.maoge.demo.web;

import com.maoge.demo.feign.ProductFeign;
import com.maoge.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController implements ProductFeign {

    private ProductService productService;

    @Transactional
    public String reduce(Integer id, Integer num){
        productService.reduce(id,num);
        return "success";
    }
}

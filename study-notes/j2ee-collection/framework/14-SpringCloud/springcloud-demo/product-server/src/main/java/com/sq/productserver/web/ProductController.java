package com.sq.productserver.web;

import com.sq.productapi.domain.Product;
import com.sq.productserver.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private IProductService productService;
    @Value("${server.port}")
    private String port;

    @RequestMapping("/api/v1/product/find")
    public Product find(Long id) {
        log.info("查找商品");
        Product product = productService.get(id);
        Product result = new Product();
        BeanUtils.copyProperties(product, result);
        result.setName(result.getName() + ", data from " + port);
        System.out.println("进入......" + port);
        return result;
    }
}

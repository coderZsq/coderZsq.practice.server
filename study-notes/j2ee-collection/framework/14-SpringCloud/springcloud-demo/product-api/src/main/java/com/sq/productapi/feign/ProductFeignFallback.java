package com.sq.productapi.feign;

import com.sq.productapi.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallback  implements ProductFeignApi{
    @Override
    public Product find(Long id) {
        System.out.println("开始进行产品兜底处理");
        Product product = new Product();
        product.setName("兜底数据");
        product.setPrice(0);
        product.setStock(0);
        return product;
    }
}

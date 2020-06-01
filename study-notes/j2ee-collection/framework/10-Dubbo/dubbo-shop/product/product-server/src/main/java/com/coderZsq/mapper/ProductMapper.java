package com.coderZsq.mapper;

import com.coderZsq.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductMapper {
    private Map<Long, Product> productMap = new HashMap<>();
    public ProductMapper() {
        Product p1 = new Product(1L, "小米手机", 2799, 10);
        Product p2 = new Product(2L, "苹果手机", 5799, 20);
        Product p3 = new Product(3L, "华为手机", 4699, 10);
        Product p4 = new Product(4L, "三星手机", 4700, 10);
        Product p5 = new Product(5L, "OPPO手机", 3799, 10);
        productMap.put(p1.getId(), p1);
        productMap.put(p2.getId(), p2);
        productMap.put(p3.getId(), p3);
        productMap.put(p4.getId(), p4);
        productMap.put(p5.getId(), p5);
    }

    public List<Product> list() {
        return new ArrayList<>(productMap.values());
    }

    public Product get(Long id) {
        return productMap.get(id);
    }
}

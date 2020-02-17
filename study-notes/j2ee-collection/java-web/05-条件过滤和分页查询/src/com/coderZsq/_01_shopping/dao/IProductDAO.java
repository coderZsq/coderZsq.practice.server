package com.coderZsq._01_shopping.dao;

import com.coderZsq._01_shopping.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductDAO {
    List<Product> list();

    /**
     * 高级查询
     * @param name 商品名称: productName LIKE '%name值%'
     * @param minSalePrice 最低价格: salePrice >= minSalePrice值
     * @param maxSalePrice 最高价格: salePrice <= maxSalePrice值
     * @return
     */
    List<Product> query(String name, BigDecimal minSalePrice, BigDecimal maxSalePrice);
}

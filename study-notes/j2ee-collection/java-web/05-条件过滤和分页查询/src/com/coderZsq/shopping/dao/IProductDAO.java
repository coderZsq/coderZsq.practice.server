package com.coderZsq.shopping.dao;

import com.coderZsq.shopping.domain.Product;
import com.coderZsq.shopping.page.PageResult;
import com.coderZsq.shopping.query.ProductQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IProductDAO {
    List<Product> list();

    /**
     * 高级查询
     *
     * @param name         商品名称: productName LIKE '%name值%'
     * @param minSalePrice 最低价格: salePrice >= minSalePrice值
     * @param maxSalePrice 最高价格: salePrice <= maxSalePrice值
     * @return
     */
    List<Product> query(String name, BigDecimal minSalePrice, BigDecimal maxSalePrice);

    /**
     * 高级查询
     */
    List<Product> query0(ProductQueryObject qo);

    /**
     * 分页查询
     *
     * @param currentPage 用户传入: 当前需要跳转到哪一页
     * @param pageSize    用户传入: 当前页最多显示多少条数据
     * @return 分页的结果对象
     */
    PageResult query(Integer currentPage, Integer pageSize);

    /**
     * 高级查询 + 分页查询
     */
    PageResult query(ProductQueryObject qo);
}

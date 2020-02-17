package com.coderZsq._01_shopping.dao.impl;

import com.coderZsq._01_shopping.dao.IProductDAO;
import com.coderZsq._01_shopping.domain.Product;
import com.coderZsq._01_shopping.handler.BeanHandler;
import com.coderZsq._01_shopping.handler.BeanListHandler;
import com.coderZsq._01_shopping.util.JdbcTemplate;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    @Override
    public List<Product> list() {
        return JdbcTemplate.query("SELECT * FROM product", new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> query(String name, BigDecimal minSalePrice, BigDecimal maxSalePrice) {
        StringBuilder sql = new StringBuilder(80);
        sql.append("SELECT * FROM product WHERE 1=1");
        // 封装占位符参数
        List<Object> parameters = new ArrayList<>();
        // 商品名称
        if (StringUtils.isNotBlank(name)) {
            sql.append(" AND productName LIKE ?");
            parameters.add("%" + name + "%");
        }
        // 最低价格
        if (minSalePrice != null) {
            sql.append(" AND salePrice >= ?");
            parameters.add(minSalePrice);
        }
        // 最高价格
        if (maxSalePrice != null) {
            sql.append(" AND salePrice <= ?");
            parameters.add(maxSalePrice);
        }
        System.out.println("SQL = " + sql);
        System.out.println("参数 = " + parameters);
        return JdbcTemplate.query(sql.toString(), new BeanListHandler<>(Product.class), parameters.toArray());
    }
}

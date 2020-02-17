package com.coderZsq._01_shopping.dao.impl;

import com.coderZsq._01_shopping.dao.IProductDAO;
import com.coderZsq._01_shopping.domain.Product;
import com.coderZsq._01_shopping.handler.BeanHandler;
import com.coderZsq._01_shopping.handler.BeanListHandler;
import com.coderZsq._01_shopping.util.JdbcTemplate;

import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    @Override
    public List<Product> list() {
        return JdbcTemplate.query("SELECT * FROM product", new BeanListHandler<>(Product.class));
    }
}

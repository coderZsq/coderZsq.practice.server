package com.coderZsq.shopping.dao.impl;

import com.coderZsq.shopping.dao.IProductDirDAO;
import com.coderZsq.shopping.domain.ProductDir;
import com.coderZsq.shopping.handler.BeanListHandler;
import com.coderZsq.shopping.util.JdbcTemplate;

import java.util.List;

public class ProductDirDAOImpl implements IProductDirDAO {
    @Override
    public List<ProductDir> list() {
        return JdbcTemplate.query("SELECT * FROM productdir", new BeanListHandler<>(ProductDir.class));
    }
}

package com.coderZsq._20_product.dao.impl;

import com.coderZsq._20_product.dao.IProductDAO;
import com.coderZsq._20_product.domain.Product;
import com.coderZsq._20_product.handler.BeanHandler;
import com.coderZsq._20_product.handler.BeanListHandler;
import com.coderZsq._20_product.util.JdbcTemplate;

import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    @Override
    public void save(Product obj) {
        JdbcTemplate.update("INSERT INTO product (productName,brand,supplier,salePrice,costPrice,cutoff,dir_id) VALUES(?,?,?,?,?,?,?)",
                obj.getProductName(), obj.getBrand(), obj.getSupplier(), obj.getSalePrice(), obj.getCostPrice(), obj.getCutoff(), obj.getDir_id());
    }

    @Override
    public void update(Product obj) {
        JdbcTemplate.update("UPDATE product SET productName=?,brand=?,supplier=?,salePrice=?,costPrice=?,cutoff=?,dir_id=? WHERE id = ?", obj.getProductName(), obj.getBrand(), obj.getSupplier(), obj.getSalePrice(), obj.getCostPrice(), obj.getCutoff(), obj.getDir_id(), obj.getId());
    }

    @Override
    public void delete(Long id) {
        JdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }

    @Override
    public Product get(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return JdbcTemplate.query(sql, new BeanHandler<>(Product.class), id);    }

    @Override
    public List<Product> list() {
        return JdbcTemplate.query("SELECT * FROM product", new BeanListHandler<>(Product.class));
    }
}

package com.coderZsq._20_product.dao.impl;

import com.coderZsq._20_product.dao.IProductDirDAO;
import com.coderZsq._20_product.domain.ProductDir;
import com.coderZsq._20_product.handler.BeanHandler;
import com.coderZsq._20_product.handler.BeanListHandler;
import com.coderZsq._20_product.util.JdbcTemplate;

import java.util.List;

public class ProductDirDAOImpl implements IProductDirDAO {

    @Override
    public void save(ProductDir obj) {
        String sql = "INSERT INTO productdir (dirName,parent_id) VALUES(?,?)";
        JdbcTemplate.update(sql, obj.getDirName(), obj.getParent_id());
    }

    @Override
    public void update(ProductDir obj) {
        String sql = "UPDATE productdir SET dirName = ?,parent_id = ? WHERE id = ?";
        JdbcTemplate.update(sql, obj.getDirName(), obj.getParent_id(), obj.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM productdir WHERE id = ?";
        JdbcTemplate.update(sql, id);
    }

    @Override
    public ProductDir get(Long id) {
        return JdbcTemplate.query("SELECT * FROM productdir WHERE id = ?", new BeanHandler<>(ProductDir.class), id);
    }

    @Override
    public List<ProductDir> list() {
        return JdbcTemplate.query("SELECT * FROM productdir", new BeanListHandler<>(ProductDir.class));
    }
}

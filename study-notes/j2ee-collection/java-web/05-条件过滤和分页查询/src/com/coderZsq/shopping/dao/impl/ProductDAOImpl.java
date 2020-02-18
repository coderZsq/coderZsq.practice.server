package com.coderZsq.shopping.dao.impl;

import com.coderZsq.shopping.dao.IProductDAO;
import com.coderZsq.shopping.domain.Product;
import com.coderZsq.shopping.handler.BeanListHandler;
import com.coderZsq.shopping.handler.IResultSetHandler;
import com.coderZsq.shopping.page.PageResult;
import com.coderZsq.shopping.query.ProductQueryObject;
import com.coderZsq.shopping.util.JdbcTemplate;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
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

    @Override
    public List<Product> query0(ProductQueryObject qo) {
        String querySql = qo.getQuery();
        List<Object> parameters = qo.getParameters();
        String sql = "SELECT * FROM product" + querySql;
        System.out.println("SQL = " + sql);
        System.out.println("参数 = " + parameters);
        return JdbcTemplate.query(sql, new BeanListHandler<>(Product.class), parameters.toArray());
    }

    @Override
    public PageResult query(Integer currentPage, Integer pageSize) {
        // 查询结果总数
        String countSql = "SELECT COUNT(*) FROM product";
        Integer totalCount = JdbcTemplate.query(countSql, new IResultSetHandler<Long>() {
            @Override
            public Long handle(ResultSet rs) throws Exception {
                if (rs.next()) {
                    return rs.getLong(1);
                }
                return 0L;
            }
        }).intValue();
        // ---------------------------------
        if (totalCount == 0) { // 说明没有符合条件的数据, 就没必要查询结果集
            return PageResult.empty(pageSize);
        }
        // 查询结果集
        String resultSql = "SELECT * FROM product LIMIT ?,?";
        Object[] params = {(currentPage - 1) * pageSize, pageSize};
        List listData = JdbcTemplate.query(resultSql, new BeanListHandler<>(Product.class), params);
        return new PageResult(currentPage, pageSize, listData, totalCount);
    }

    // ============================================
    @Override
    public PageResult query(ProductQueryObject qo) {
        // 查询结果总数
        String countSql = "SELECT COUNT(*) FROM product" + qo.getQuery();
        Integer totalCount = JdbcTemplate.query(countSql, new IResultSetHandler<Long>() {
            @Override
            public Long handle(ResultSet rs) throws Exception {
                if (rs.next()) {
                    return rs.getLong(1);
                }
                return 0L;
            }
        }, qo.getParameters().toArray()).intValue();
        // ---------------------------------
        if (totalCount == 0) { // 说明没有符合条件的数据, 就没必要查询结果集
            return PageResult.empty(qo.getPageSize());
        }
        System.out.println("countSql = " + countSql);
        System.out.println("参数 = " + qo.getParameters());
        // ---------------------------------
        // 查询结果集
        String resultSql = "SELECT * FROM product " + qo.getQuery() +" LIMIT ?,?";
        // 增加LIMIT的两个占位符参数
        qo.getParameters().add((qo.getCurrentPage() - 1) * qo.getPageSize()); // LIMIT第一个?
        qo.getParameters().add(qo.getPageSize()); // LIMIT第二个?
        List listData = JdbcTemplate.query(resultSql, new BeanListHandler<>(Product.class), qo.getParameters().toArray());
        System.out.println("resultSql = " + resultSql);
        System.out.println("参数 = " + qo.getParameters());
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), listData, totalCount);
    }
    // ============================================
}

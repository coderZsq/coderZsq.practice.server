package com.coderZsq.shopping.query;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 封装了商品的对象的查询条件
@Data
public class ProductQueryObject {
    private String name;
    private BigDecimal minSalePrice;
    private BigDecimal maxSalePrice;

    // 封装查询条件
    private List<String> conditions = new ArrayList<>();

    // 封装占位符参数
    private List<Object> parameters = new ArrayList<>();

    // 返回查询条件, 如: WHERE productName LIKE ? AND salePrice >= ?
    public String getQuery0() {
        StringBuilder sql = new StringBuilder(80);
        sql.append(" WHERE 1=1");
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
        return sql.toString();
    }

    public String getQuery1() {
        StringBuilder sql = new StringBuilder(80);
        // 商品名称
        if (StringUtils.isNotBlank(name)) {
            conditions.add("productName LIKE ?");
            parameters.add("%" + name + "%");
        }
        // 最低价格
        if (minSalePrice != null) {
            conditions.add("salePrice >= ?");
            parameters.add(minSalePrice);
        }
        // 最高价格
        if (maxSalePrice != null) {
            conditions.add("salePrice <= ?");
            parameters.add(maxSalePrice);
        }
        // ----------------------------------
        if (conditions.size() == 0) {
            return "";
        }
        for (int i = 0; i < conditions.size(); i++) {
            if (i == 0) { // 第一个条件
                sql.append(" WHERE ");
            } else { // 非第一个条件
                sql.append(" AND ");
            }
            sql.append(conditions.get(i));
        }
        // ----------------------------------
        return sql.toString();
    }

    public String getQuery() {
        StringBuilder sql = new StringBuilder(80);
        // 商品名称
        if (StringUtils.isNotBlank(name)) {
            conditions.add("productName LIKE ?");
            parameters.add("%" + name + "%");
        }
        // 最低价格
        if (minSalePrice != null) {
            conditions.add("salePrice >= ?");
            parameters.add(minSalePrice);
        }
        // 最高价格
        if (maxSalePrice != null) {
            conditions.add("salePrice <= ?");
            parameters.add(maxSalePrice);
        }
        // ----------------------------------
        if (conditions.size() == 0) {
            return "";
        }
        String queryString = StringUtils.join(conditions, " AND ");
        // ----------------------------------
        return sql.append(" WHERE ").append(queryString).toString();
    }

    // 返回查询条件中的占位符参数值
    public List<Object> getParameters() {
        return parameters;
    }
}

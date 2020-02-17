package com.coderZsq.shopping.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

// 封装了商品的对象的查询条件
@Getter@Setter
public class ProductQueryObject extends QueryObject {
    private String name;
    private BigDecimal minSalePrice;
    private BigDecimal maxSalePrice;
    private Long dirId = -1L;
    private String keyword; // 关键字

    @Override
    public void customizedQuery() {
        // 商品名称
        if (StringUtils.isNotBlank(name)) {
            super.addQuery("productName LIKE ?", "%" + name + "%");
        }
        // 最低价格
        if (minSalePrice != null) {
            super.addQuery("salePrice >= ?", minSalePrice);
        }
        // 最高价格
        if (maxSalePrice != null) {
            super.addQuery("salePrice <= ?", maxSalePrice);
        }
        // 商品分类
        if (dirId != -1) {
            super.addQuery("dir_id = ?", dirId);
        }
        // 关键字
        if (StringUtils.isNotBlank(keyword)) {
            super.addQuery("(productName LIKE ? OR brand LIKE ?)", "%" + keyword + "%", "%" + keyword + "%");
        }
    }
    /*
    // 自身的定制查询
    public void customizedQuery() {
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
        // 商品分类
        if (dirId != -1) {
            conditions.add("dir_id = ?");
            parameters.add(dirId);
        }
    }
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

    public String getQuery2() {
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
        // 商品分类
        if (dirId != -1) {
            conditions.add("dir_id = ?");
            parameters.add(dirId);
        }
        // ----------------------------------
        if (conditions.size() == 0) {
            return "";
        }
        String queryString = StringUtils.join(conditions, " AND ");
        // ----------------------------------
        return sql.append(" WHERE ").append(queryString).toString();
    }

    public String getQuery3() {
        StringBuilder sql = new StringBuilder(80);
        this.customizedQuery();
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
     */
}

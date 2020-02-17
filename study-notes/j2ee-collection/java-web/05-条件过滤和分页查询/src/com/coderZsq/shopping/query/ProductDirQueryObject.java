package com.coderZsq.shopping.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

// 封装了商品分类的对象的查询条件
@Getter@Setter
public class ProductDirQueryObject extends QueryObject {
    private String name;
    private Long parentId = -1L;

    // 自身的定制查询
    @Override
    public void customizedQuery() {
        // 分类名称
        if (StringUtils.isNotBlank(name)) {
            super.addQuery("dirname LIKE ?", "%" + name + "%");
        }
        // 父分类
        if (parentId != -1) {
            super.addQuery("parent_id = ?", parentId);
        }
    }
    /*
    // 封装查询条件
    private List<String> conditions = new ArrayList<>();

    // 封装占位符参数
    private List<Object> parameters = new ArrayList<>();

    // 返回查询条件, 如: WHERE productName LIKE ? AND salePrice >= ?
    public String getQuery() {
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

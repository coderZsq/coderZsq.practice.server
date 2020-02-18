package com.coderZsq.shopping.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 高级查询对象的基类, 包含所有查询对象的共性
public class QueryObject {
    @Getter@Setter
    private Integer currentPage = 1;
    @Getter@Setter
    private Integer pageSize = 5;

    // 封装查询条件
    private List<String> conditions = new ArrayList<>();

    // 封装占位符参数
    private List<Object> parameters = new ArrayList<>();

    private boolean isBuild = false; // 是否已经构建SQL/封装参数

    // 初始化方法
    private void init() {
        if (!isBuild) { // 如果没有构建过
            customizedQuery(); // 构建
            isBuild = true;
        }

    }
    public String getQuery() {
        this.init();
        StringBuilder sql = new StringBuilder(80);
        // this.customizedQuery();
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
        this.init();
        return parameters;
    }

    // 暴露给子类, 让子类覆盖编写自个的查询方式
    protected void customizedQuery() {

    }

    // 暴露给子类, 让子类在customizedQuery中调用, 添加自己的查询条件和参数
    protected void addQuery(String condition, Object... params) {
        this.conditions.add(condition);
        this.parameters.addAll(Arrays.asList(params));
    }
}

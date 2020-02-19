package com.coderZsq.shopping.query;

import java.util.List;

// 表示查询对象的规范, 约束查询对象应该具有的方法
public interface IQuery {
    String getQuery();

    List<Object> getParameters();

    Integer getPageSize();

    Integer getCurrentPage();
}

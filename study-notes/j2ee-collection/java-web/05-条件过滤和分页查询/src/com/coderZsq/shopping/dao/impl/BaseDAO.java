package com.coderZsq.shopping.dao.impl;

import com.coderZsq.shopping.domain.Table;
import com.coderZsq.shopping.handler.BeanListHandler;
import com.coderZsq.shopping.handler.IResultSetHandler;
import com.coderZsq.shopping.page.PageResult;
import com.coderZsq.shopping.query.IQuery;
import com.coderZsq.shopping.util.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

@SuppressWarnings("all")
public class BaseDAO {
    public PageResult query(Class<?> classType, IQuery qo) {
        // 默认把类名作为表名
        String tableName = classType.getSimpleName();
        Table table = classType.getAnnotation(Table.class);
        if (table != null) {
            tableName = table.value();
        }
        // 查询结果总数
        String countSql = "SELECT COUNT(*) FROM " + tableName + qo.getQuery();
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
        String resultSql = "SELECT * FROM " + tableName + qo.getQuery() +" LIMIT ?,?";
        // 增加LIMIT的两个占位符参数
        qo.getParameters().add((qo.getCurrentPage() - 1) * qo.getPageSize()); // LIMIT第一个?
        qo.getParameters().add(qo.getPageSize()); // LIMIT第二个?
        List listData = JdbcTemplate.query(resultSql, new BeanListHandler<>(classType), qo.getParameters().toArray());
        System.out.println("resultSql = " + resultSql);
        System.out.println("参数 = " + qo.getParameters());
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), listData, totalCount);
    }
}

package com.coderZsq._14_hibernate.handler;

import java.sql.ResultSet;

// 结果集处理器, 规范处理结果集的方法名称
public interface IResultSetHandler<T> {
    // 处理结果集
    T handle(ResultSet rs) throws Exception;
}
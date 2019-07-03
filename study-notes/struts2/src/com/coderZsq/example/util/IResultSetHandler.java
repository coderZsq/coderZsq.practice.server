package com.coderZsq.example.util;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
    T handle(ResultSet resultSet) throws Exception;
}

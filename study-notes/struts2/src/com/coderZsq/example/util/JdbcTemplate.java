package com.coderZsq.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {

    public static <T> T query(String sql, IResultSetHandler<T> resultSetHandler, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int index = 0; index < params.length; index++) {
                preparedStatement.setObject(index + 1, params[index]);
            }
            resultSet = preparedStatement.executeQuery();
            return resultSetHandler.handle(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, null);
        }
        return null;
    }

    public static int update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int index = 0; index < params.length; index++) {
                preparedStatement.setObject(index + 1, params[index]);
            }
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, null);
        }
        return 0;
    }
}

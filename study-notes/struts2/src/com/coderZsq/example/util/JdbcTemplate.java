package com.coderZsq.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JdbcTemplate {

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

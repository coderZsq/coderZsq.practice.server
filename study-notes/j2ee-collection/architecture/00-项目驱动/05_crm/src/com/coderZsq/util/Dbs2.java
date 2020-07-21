package com.coderZsq.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dbs2 {
    private static String url;
    private static String username;
    private static String password;
    private static String driverClassName;
    static { // IO
        try (InputStream is = Dbs2.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driverClassName = properties.getProperty("driverClassName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行DDL、DML语句
     *
     * @return
     */
    public static int update(String sql, Object... args) {
        try {
            Class.forName(driverClassName);
            // 从数据库获取所有的客户数据
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 设置参数
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                }
                // 执行
                return pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static <T> List<T> query(String sql, RowMapper<T> mapper, Object... args) {
        if (mapper == null) return null;
        try {
            Class.forName(driverClassName);
            // 从数据库获取所有的客户数据
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 设置参数
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                }
                // 执行
                ResultSet rs = pstmt.executeQuery();
                List<T> array = new ArrayList<>();
                for (int row = 0; rs.next(); row++) {
                    array.add(mapper.map(rs, row));
                }
                rs.close();
                return array;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用来执行每一行数据的映射 （rs -> bean)
     * @param <T>
     */
    public interface RowMapper<T> {
        T map(ResultSet rs, int row) throws Exception;
    }
}

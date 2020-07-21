package com.coderZsq.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dbs {
    private static DataSource ds;
    static {
        try (InputStream is = Dbs.class.getClassLoader().getResourceAsStream("druid.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
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
            Connection conn = ds.getConnection();
            // 从数据库获取所有的客户数据
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
            Connection conn = ds.getConnection();
            // 从数据库获取所有的客户数据
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 设置参数
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                }
                // 执行
                List<T> array = new ArrayList<>();
                try (ResultSet rs = pstmt.executeQuery()) {
                    for (int row = 0; rs.next(); row++) {
                        array.add(mapper.map(rs, row));
                    }
                }
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

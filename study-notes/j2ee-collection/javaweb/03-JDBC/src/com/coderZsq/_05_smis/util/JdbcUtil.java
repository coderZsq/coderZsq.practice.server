package com.coderZsq._05_smis.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

// JDBC的工具类
public class JdbcUtil {
    // 连接数据库的四要素
    private static Properties p = new Properties();

    static {
        // 当JdbcUtil这份字节码被加载进JVM就立刻执行
        try {
            // 加载和读取db.properties文件
            InputStream inStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("db.properties");
            p.load(inStream); // 加载
            // ---------------------------------
            Class.forName(p.getProperty("driverClassName")); // 获取driverClassName的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建并返回一个Connection对象
    public static Connection getConn() {
        try {
            return DriverManager.getConnection(
                    p.getProperty("url"), // 获取url的值
                    p.getProperty("username"), // 获取username的值
                    p.getProperty("password")); // 获取password的值
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 释放资源
    public static void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {

                }
            }
        }
    }
}

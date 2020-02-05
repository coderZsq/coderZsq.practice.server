package com.coderZsq._12_datasource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBCPUtil {
    private static DataSource ds = null;

    static {
        try {
            Properties p = new Properties();
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp.properties"));
            // 创建DataSource对象
            ds = BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            // 从连接池中获取Connection对象
            return ds.getConnection();
        } catch (SQLException e) {
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

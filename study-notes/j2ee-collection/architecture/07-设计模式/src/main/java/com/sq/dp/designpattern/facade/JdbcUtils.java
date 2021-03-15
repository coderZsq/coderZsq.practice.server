package com.sq.dp.designpattern.facade;

import java.sql.*;

/**
 * 外观类: 将子组件的功能实现做同一封装
 */
public class JdbcUtils {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1::3306/test", "root", "admin");
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

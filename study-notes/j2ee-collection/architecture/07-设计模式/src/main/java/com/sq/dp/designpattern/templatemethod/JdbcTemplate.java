package com.sq.dp.designpattern.templatemethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 抽象模板类
 * update: 具体方法 -> 实现方法
 * query: 模板方法
 * processR欧文: 抽象方法
 */
abstract public class JdbcTemplate<T> {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1::3306/test", "root", "admin");
            ;
            st = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 1, len = params.length; i <= len; i++) {
                    st.setObject(i, params[i]);
                }
            }
            return st.executeUpdate();
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
        return -1;
    }

    public List<T> query(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1::3306/test", "root", "admin");
            ;
            st = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 1, len = params.length; i <= len; i++) {
                    st.setObject(i, params[i]);
                }
            }
            return processRow(st.executeQuery());
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
        return null;
    }

    abstract public List<T> processRow(ResultSet rs);
}

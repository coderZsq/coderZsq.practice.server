package com.coderZsq._02_ddl_exception;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DDLTest {
    // 创建t_student表
    @Test
    public void testCreateTable() throws Exception {
        String sql = "CREATE TABLE t_student (id bigint(20) PRIMARY KEY AUTO_INCREMENT, name varchar(20), age int(11));";
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
        // 3. 创建语句对象
        Statement st = conn.createStatement();
        // 4. 执行SQL语句
        int row = st.executeUpdate(sql);
        // 5. 释放资源
        st.close();
        conn.close();
        System.out.println(row);
    }

    // 正确处理异常
    @Test
    public void testHandleException() {
        String sql = "CREATE TABLE t_student (id bigint(20) PRIMARY KEY AUTO_INCREMENT, name varchar(20), age int(11));";

        // 声明需要关闭的资源
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            // 3. 创建语句对象
            st = conn.createStatement();
            // 4. 执行SQL语句
            st.executeUpdate(sql);
        } catch (Exception e) {
        } finally {
            // 5. 释放资源
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

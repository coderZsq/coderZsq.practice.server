package com.sq.demo.mysqldemo.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";
        // 创建一个连接
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement st = null;
        try {
            // 设置手动提交事务
            connection.setAutoCommit(false);
            // 执行的sql语句, 业务操作
            st = connection.prepareStatement("insert into user(name) values ('test007')");
            // 执行
            int count = st.executeUpdate();
            System.out.println("count = " + count);
            connection.commit(); // 持久化 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback(); // 回滚事务
        } finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

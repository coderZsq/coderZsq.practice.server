package com.coderZsq._01_connection;


import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {
    // 获取数据库连接对象
    @Test
    public void test1() throws Exception {
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc://mysql://localhost:3306/jdbc", "root", "root");
    }
}

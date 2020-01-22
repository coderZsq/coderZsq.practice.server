package com.coderZsq._03_dml;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DMLTest {
    // 需求1: 往t_student表中插入, 乔峰/30
    @Test
    public void testInsert() throws Exception {
        String sql = "INSERT INTO t_student (name, age) VALUES ('乔峰', 30);";
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
        // 3. 创建语句对象
        Statement st = conn.createStatement();
        // 4. 执行SQL语句
        int row = st.executeUpdate(sql);
        // 5. 释放资源
        st.close();
        conn.close();
        System.out.println(row);
    }
    // 需求2: 把id为1的学生的名字改为西门吹雪, 年龄改为28
    @Test
    public void testUpdate() throws Exception {
        String sql = "UPDATE t_student SET name = '西门吹雪', age = 28 WHERE id = 1";
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
        // 3. 创建语句对象
        Statement st = conn.createStatement();
        // 4. 执行SQL语句
        int row = st.executeUpdate(sql);
        // 5. 释放资源
        st.close();
        conn.close();
        System.out.println(row);
    }
    // 需求3: 把id为1的学生删除掉
    @Test
    public void testDelete() throws Exception {
        String sql = "DELETE FROM t_student WHERE id = 1";
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
        // 3. 创建语句对象
        Statement st = conn.createStatement();
        // 4. 执行SQL语句
        int row = st.executeUpdate(sql);
        // 5. 释放资源
        st.close();
        conn.close();
        System.out.println(row);
    }
}

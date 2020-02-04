package com.coderZsq._04_dql;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DQLTest {
    @Test
    public void test0() throws Exception {
        // 取出list集合中每一个元素
        List<String> list = Arrays.asList("A", "B", "C", "D");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next()); // 获取光标后的元素, 移动光标
        }
    }
    // 需求1: 查询t_student表中一共有多少条学生信息
    @Test
    public void test1() throws Exception {
//        String sql = "SELECT COUNT(id) FROM t_student";
        String sql = "SELECT COUNT(id) rows FROM t_student";
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
        // 3. 创建语句对象
        Statement st = conn.createStatement();
        // -----------------------------------
        // 4. 执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        // 处理结果集
        if (rs.next()) {
//            long totalCount = rs.getLong(1); // 获取第1列
//            long totalCount = rs.getLong("COUNT(id)");
            long totalCount = rs.getLong("rows");
            System.out.println(totalCount);
        }
        // -----------------------------------
        // 5. 释放资源
        rs.close();
        st.close();
        conn.close();
    }
    // 需求2: 查询t_student表中id为1的学生信息
    @Test
    public void test2() throws Exception {
//        String sql = "SELECT COUNT(id) FROM t_student";
        String sql = "SELECT * FROM t_student  WHERE id = 1";
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
        // 3. 创建语句对象
        Statement st = conn.createStatement();
        // -----------------------------------
        // 4. 执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        // 处理结果集
        if (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(id + "," + name + "," + age);
        }
        // -----------------------------------
        // 5. 释放资源
        rs.close();
        st.close();
        conn.close();
    }
    // 需求3: 查询t_student表中所有学生的信息
    @Test
    public void test3() throws Exception {
//        String sql = "SELECT COUNT(id) FROM t_student";
        String sql = "SELECT * FROM t_student";
        // 1. 加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc", "root", "root");
        // 3. 创建语句对象
        Statement st = conn.createStatement();
        // -----------------------------------
        // 4. 执行SQL语句
        ResultSet rs = st.executeQuery(sql);
        // 处理结果集
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(id + "," + name + "," + age);
        }
        // -----------------------------------
        // 5. 释放资源
        rs.close();
        st.close();
        conn.close();
    }
}

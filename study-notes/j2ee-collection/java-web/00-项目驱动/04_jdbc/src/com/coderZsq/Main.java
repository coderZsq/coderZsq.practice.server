package com.coderZsq;

import java.sql.*;

public class Main {
    // 0. 数据库相关的信息
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver?serverTimezone=UTC";
    private static final String URL = "jdbc:mysql://localhost:3306/xmg";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        // SQL Injection
        // SQL注入问题
        String username = "";
        String password = "' OR '1' = '1";
        login2(username, password);
    }

    private static void login2(String username, String password) throws Exception {
        /*
           SELECT * FROM user WHERE username = '' AND password = '\' OR \'1\' = \'1'
           SELECT * FROM user WHERE username = "" AND password = "' OR '1' = '1"
         */
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // 3: 编译, 解析, 优化
            // 设置参数值
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // 执行SQL语句
            ResultSet rs = pstmt.executeQuery(); // 执行
            ResultSet rs2 = pstmt.executeQuery(); // 执行
            ResultSet rs3 = pstmt.executeQuery(); // 执行
            if (rs.next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败, 用户名或密码不正确");
            }
        }
    }

    private static void login1(String username, String password) throws Exception {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败, 用户名或密码不正确");
            }
        }
    }

    private static void test4() throws Exception {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT id myId, name myName FROM student";
            ResultSet rs = stmt.executeQuery(sql); // 4: 编译, 解析, 优化, 执行
            ResultSet rs2 = stmt.executeQuery(sql); // 4: 编译, 解析, 优化, 执行
            ResultSet rs3 = stmt.executeQuery(sql); // 4: 编译, 解析, 优化, 执行

            while (rs.next()) {
                System.out.println(rs.getInt("myId"));
                System.out.println(rs.getString("myName"));
                // System.out.println(rs.getInt("age"));
                // System.out.println(rs.getString("birthday"));
                // System.out.println(rs.getString("phone"));
                // System.out.println(rs.getString("email"));
                // System.out.println(rs.getString("intro"));

                System.out.println("--------------------------------");
            }
        }
    }

    private static void test3() throws Exception {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(sql);
            // 让游标指向第1行记录
            rs.next();

            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("name"));
            System.out.println(rs.getInt("age"));
            System.out.println(rs.getString("birthday"));
            System.out.println(rs.getString("phone"));
            System.out.println(rs.getString("email"));
            System.out.println(rs.getString("intro"));

            System.out.println("--------------------------------");

            // 让游标指向第2行记录
            rs.next();

            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("name"));
            System.out.println(rs.getInt("age"));
            System.out.println(rs.getString("birthday"));
            System.out.println(rs.getString("phone"));
            System.out.println(rs.getString("email"));
            System.out.println(rs.getString("intro"));
        }
    }

    private static void test2() {
        // JDK7开始 try-with-resource
        try {
            // Class.forName(driverClassName);
            // DriverManager.registerDriver(new Driver());
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                stmt.execute("UPDATE student SET age = 100 WHERE id = 1");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test1() throws Exception {
        // 0. 数据库相关的信息
        // String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/xmg?serverTimezone=UTC";
        String username = "root";
        String password = "root";

        // 1. 注册Driver到DriverManager
        // Class.forName(driverClassName);
        // DriverManager.registerDriver(new Driver());

        // 2. 利用DriverManager创建数据库连接
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 利用Connection创建Statement
        Statement stmt = conn.createStatement();

        // 4. 利用Statement来执行SQL语句
        stmt.execute("UPDATE student SET age = 100 WHERE id = 1");

        // 5. 关闭资源
        stmt.close();
        conn.close();
    }
}

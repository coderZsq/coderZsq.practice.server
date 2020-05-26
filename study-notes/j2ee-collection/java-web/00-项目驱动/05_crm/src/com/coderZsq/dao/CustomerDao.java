package com.coderZsq.dao;

import com.coderZsq.Constants;
import com.coderZsq.bean.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    /**
     * 将customer保存到数据库
     */
    public boolean save(Customer customer) {
        try {
            String sql = "INSERT INTO customer(name, age, height) VALUES (?, ?, ?)";
            Class.forName("com.mysql.jdbc.Driver");
            // 从数据库获取所有的客户数据
            try (Connection conn = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, customer.getName());
                pstmt.setInt(2, customer.getAge());
                pstmt.setDouble(3, customer.getHeight());
                return pstmt.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回所有的customer数据
     */
    public List<Customer> list() {
        try {
            String sql = "SELECT id, name, age, height FROM customer";
            Class.forName("com.mysql.jdbc.Driver");
            // 从数据库获取所有的客户数据
            try (Connection conn = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                List<Customer> customers = new ArrayList<>();
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setAge(rs.getInt("age"));
                    customer.setHeight(rs.getDouble("height"));
                    customers.add(customer);
                }
                return customers;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.coderZsq.dao;

import com.coderZsq.bean.Customer;
import com.coderZsq.util.Dbs;

import java.util.List;

// Data Access Object
public class CustomerDao {
    /**
     * 将customer保存到数据库
     */
    public boolean save(Customer customer) {
        String sql = "INSERT INTO customer(name, age, height) VALUES (?, ?, ?)";
        return Dbs.update(sql, customer.getName(), customer.getAge(), customer.getHeight()) > 0;
    }

    /**
     * 返回所有的customer数据
     */
    public List<Customer> list() {
        String sql = "SELECT id, name, age, height FROM customer";
        return Dbs.query(sql, (rs, row) -> {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setAge(rs.getInt("age"));
            customer.setHeight(rs.getDouble("height"));
            return customer;
        });
    }
}

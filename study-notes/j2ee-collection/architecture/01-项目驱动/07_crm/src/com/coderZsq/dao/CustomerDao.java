package com.coderZsq.dao;

import com.coderZsq.bean.Customer;
import com.coderZsq.util.Dbs;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

// Data Access Object
public class CustomerDao {
    /**
     * 将customer添加\更新到数据库
     */
    public boolean save(Customer customer) {
        // 构建参数
        List<Object> args = new ArrayList<>();
        args.add(customer.getName());
        args.add(customer.getRealAge());
        args.add(customer.getHeight());

        String sql;

        // 根据id识别添加 or 更新
        Integer id = customer.getId();
        if (id == null || id < 1) { // 添加
            sql = "INSERT INTO customer(name, real_age, height) VALUES (?, ?, ?)";
        } else { // 更新
            sql = "UPDATE customer SET name = ?, real_age = ?, height = ? WHERE id = ?";
            args.add(id);
        }
        return Dbs.getTpl().update(sql, args.toArray()) > 0;
    }

    /**
     * 返回所有的customer数据
     */
    public List<Customer> list() {
        String sql = "SELECT id, name, real_age, height FROM customer";
        return Dbs.getTpl().query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    /**
     * 删除customer
     * @param id
     */
    public boolean remove(Integer id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        return Dbs.getTpl().update(sql, id) > 0;
    }

    public Customer find(Integer id) {
        String sql = "SELECT id, name, real_age, height FROM customer WHERE id = ?";
        return Dbs.getTpl().queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), id);
    }
}

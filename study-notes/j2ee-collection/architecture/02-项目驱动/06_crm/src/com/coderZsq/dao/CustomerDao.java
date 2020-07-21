package com.coderZsq.dao;

import com.coderZsq.bean.Customer;
import com.coderZsq.util.Dbs;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

// Data Access Object
public class CustomerDao {
    /**
     * 将customer保存到数据库
     */
    public boolean save(Customer customer) {
        String sql = "INSERT INTO customer(name, real_age, height) VALUES (?, ?, ?)";
        List<Object> args = buildArgs(customer);
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
        // return Dbs.getTpl().query(sql, new BeanPropertyRowMapper<>(Customer.class), id).get(0);
    }

    public boolean update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, real_age = ?, height = ? WHERE id = ?";
        List<Object> args = buildArgs(customer);
        args.add(customer.getId());
        return Dbs.getTpl().update(sql, args.toArray()) > 0;
    }

    private List<Object> buildArgs(Customer customer) {
        List<Object> args = new ArrayList<>();
        args.add(customer.getName());
        args.add(customer.getRealAge());
        args.add(customer.getHeight());
        return args;
    }
}

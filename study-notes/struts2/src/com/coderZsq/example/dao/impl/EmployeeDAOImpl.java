package com.coderZsq.example.dao.impl;

import com.coderZsq.example.dao.IEmployeeDAO;
import com.coderZsq.example.domain.Employee;
import com.coderZsq.example.query.EmployeeQueryObject;
import com.coderZsq.example.util.BeanHandler;
import com.coderZsq.example.util.BeanListHandler;
import com.coderZsq.example.util.JdbcTemplate;

import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {
    @Override
    public void save(Employee e) {
        JdbcTemplate.update("INSERT INTO t_employee(name,password,salary) values (?,?,?)", e.getName(), e.getPassword(), e.getSalary());
    }

    @Override
    public void update(Employee e) {
        JdbcTemplate.update("UPDATE t_employee SET name = ?, password = ?, salary = ? WHERE id = ?", e.getName(), e.getPassword(), e.getSalary(), e.getId());
    }

    @Override
    public void delete(Employee e) {
        JdbcTemplate.update("DELETE FROM t_employee WHERE id = ?", e.getId());
    }

    @Override
    public Employee get(Long id) {
        return JdbcTemplate.query("SELECT * FROM t_employee WHERE id = ?", new BeanHandler<>(Employee.class), id);
    }

    @Override
    public List<Employee> listAll() {
        return JdbcTemplate.query("SELECT * FROM t_employee", new BeanListHandler<>(Employee.class));
    }

    @Override
    public List<Employee> query(EmployeeQueryObject queryObject) {
        String sql = "SELECT * FROM t_employee" + queryObject.getQuery();
        System.out.println("SQL = " + sql);
        System.out.println("params = " + queryObject.getParameters());
        return JdbcTemplate.query(sql, new BeanListHandler<>(Employee.class), queryObject.getParameters().toArray());
    }
}

package com.coderZsq.example.dao.impl;

import com.coderZsq.example.dao.IEmployeeDAO;
import com.coderZsq.example.domain.Employee;
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
        return null;
    }

    @Override
    public List<Employee> listAll() {
        return null;
    }
}

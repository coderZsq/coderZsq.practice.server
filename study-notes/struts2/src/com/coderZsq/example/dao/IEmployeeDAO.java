package com.coderZsq.example.dao;

import com.coderZsq.example.domain.Employee;
import com.coderZsq.example.query.EmployeeQueryObject;
import com.coderZsq.example.query.PageResult;

import java.util.List;

public interface IEmployeeDAO {
    void save(Employee e);

    void update(Employee e);

    void delete(Employee e);

    Employee get(Long id);

    List<Employee> listAll();

    List<Employee> query1(EmployeeQueryObject queryObject);

    PageResult query(EmployeeQueryObject queryObject);
}

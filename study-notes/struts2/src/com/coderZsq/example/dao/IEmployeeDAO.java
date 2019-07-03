package com.coderZsq.example.dao;

import com.coderZsq.example.domain.Employee;
import com.coderZsq.example.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeDAO {
    void save(Employee e);

    void update(Employee e);

    void delete(Employee e);

    Employee get(Long id);

    List<Employee> listAll();

    List<Employee> query(EmployeeQueryObject queryObject);
}

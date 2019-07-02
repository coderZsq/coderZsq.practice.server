package com.coderZsq.example.dao;

import com.coderZsq.example.domain.Employee;

import java.util.List;

public interface IEmployeeDAO {
    void save(Employee e);

    void update(Employee e);

    void delete(Employee e);

    Employee get(Long id);

    List<Employee> listAll();
}

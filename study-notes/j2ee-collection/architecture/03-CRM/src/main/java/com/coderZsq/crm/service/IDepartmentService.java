package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.Department;

import java.util.List;

public interface IDepartmentService {
    public List<Department> queryAll();

    public int insert(Department department);
}

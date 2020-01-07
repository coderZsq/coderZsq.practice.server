package com.coderZsq.rbac.service;

import com.coderZsq.rbac.domain.Department;

import java.util.List;

public interface IDepartmentService {
    public List<Department> selectAll();

    public void insert(Department department);
}
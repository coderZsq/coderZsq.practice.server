package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    public List<Department> queryAll();

    public int insert(Department department);
}

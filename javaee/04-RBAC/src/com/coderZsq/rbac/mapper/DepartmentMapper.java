package com.coderZsq.rbac.mapper;

import com.coderZsq.rbac.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    public List<Department> selectAll();

    public int insert(Department department);

}

package com.coderZsq.rbac.mapper;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    public List<Department> selectAll();

    public int insert(Department department);

    List<Department> query(QueryObject queryObject);
}

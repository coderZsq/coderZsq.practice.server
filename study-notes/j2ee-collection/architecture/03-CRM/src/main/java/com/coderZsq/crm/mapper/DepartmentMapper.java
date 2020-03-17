package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.query.DepartmentQueryObject;

import java.util.List;

public interface DepartmentMapper {
    public List<Department> queryAll();

    public int insert(Department department);

    public void delete(Long id);

    public void update(Department department);

    List<Department> query(DepartmentQueryObject qo);
}

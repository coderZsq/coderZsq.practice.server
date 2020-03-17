package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.query.DepartmentQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    public List<Department> queryAll();

    public int insert(Department department);

    void delete(Long id);

    void save(Department department);

    void update(Department department);

    PageInfo query(DepartmentQueryObject qo);
}

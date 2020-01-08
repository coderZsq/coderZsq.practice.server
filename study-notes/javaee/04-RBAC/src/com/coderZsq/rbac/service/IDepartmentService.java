package com.coderZsq.rbac.service;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    public List<Department> selectAll();

    public void insert(Department department);

    PageInfo query(QueryObject queryObject);

    void delete(Long id);
}
package com.coderZsq.rbac.service.impl;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.mapper.DepartmentMapper;
import com.coderZsq.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public void insert(Department department) {
        departmentMapper.insert(department);
    }
}

package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.mapper.DepartmentMapper;
import com.coderZsq.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired // @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> queryAll() {
        return departmentMapper.queryAll();
    }

    @Override
    public int insert(Department department) {
        int count = departmentMapper.insert(department);
        // int i = 1 / 0; // 验证事务
        return count;
    }
}

package com.coderZsq.rbac.service.impl;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.mapper.DepartmentMapper;
import com.coderZsq.rbac.query.QueryObject;
import com.coderZsq.rbac.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo query(QueryObject queryObject) {
        // 查询之前需要先配置一个查询的当前页, 每页数据的大小
        PageHelper.startPage(queryObject.getCurrentPage(), queryObject.getPageSize());
        List<Department> departments = departmentMapper.query(queryObject); // 通过拦截器进行分页查询
        return new PageInfo<Department>(departments);
    }
}

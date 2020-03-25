package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.mapper.DepartmentMapper;
import com.coderZsq.crm.query.DepartmentQueryObject;
import com.coderZsq.crm.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
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

    @Override
    public void delete(Long id) {
        departmentMapper.delete(id);
    }

    @Override
    public void save(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public PageInfo query(DepartmentQueryObject qo) {
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Department> list = departmentMapper.query(qo);
        //用PageInfo对结果进行包装
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public Department queryByName(String name) {
        return departmentMapper.queryByName(name);
    }
}

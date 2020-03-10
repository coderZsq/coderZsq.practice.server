package com.coderZsq.rbac.service.impl;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.mapper.DepartmentMapper;
import com.coderZsq.rbac.qo.PageResult;
import com.coderZsq.rbac.qo.QueryObject;
import com.coderZsq.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper mapper;//NullPointException

    @Override
    public void saveOrUpdate(Department department) {
        if (department.getId() == null) {
            mapper.insert(department);
        } else {
            mapper.updateByPrimaryKey(department);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Department getById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> getAllList() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Department> selectByQuery(QueryObject qo) {
        //判断查询条数
        int count = mapper.selectByCount();
        if (count == 0){
            return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),0, Collections.emptyList());
        }

        List<Department> departments = mapper.selectByQuery(qo);
        return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),count, departments);
    }
}

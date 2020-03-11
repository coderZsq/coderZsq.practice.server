package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.utils.PageResult;
import com.coderZsq.rbac.query.QueryObject;
import com.coderZsq.rbac.service.IDepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("queryAll") // /department/queryAll
    public PageResult<List<Department>> queryAll() {
        return PageResult.success(departmentService.selectAll());
    }

    @RequestMapping("query")
    public PageResult<PageInfo<Department>> query(QueryObject queryObject) {
        // 查询
        PageInfo<Department> page = departmentService.query(queryObject);
        return PageResult.success(page);
    }

    @RequestMapping("delete")
    public PageResult<Boolean> delete(Long id) {
        departmentService.delete(id);
        return PageResult.success(true);
    }

    @RequestMapping("saveOrUpdate")
    public PageResult<Boolean> saveOrUpdate(Department department) {
        // 是否存在Id
        Long id = department.getId();
        if (id == null) { // 新增操作
            departmentService.insert(department);
        } else {
            departmentService.update(department);
        }
        return PageResult.success(true);
    }
}
package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.query.PageResult;
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

    @RequestMapping("selectAll") // /department/selectAll
    public List<Department> selectAll() {
        return departmentService.selectAll();
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
}
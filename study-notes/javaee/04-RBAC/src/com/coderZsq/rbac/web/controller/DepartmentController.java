package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.service.IDepartmentService;
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
}
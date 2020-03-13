package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @RequestMapping("queryAll")
    public String queryAll(Model model) {
        List<Department> departments = departmentService.queryAll();
        model.addAttribute("depts", departments);
        return "department/list"; // 逻辑视图 --> 物理视图 1 指定目录(后缀), 2 指定前缀
    }
}

package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.query.DepartmentQueryObject;
import com.coderZsq.crm.service.IDepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("list")
    public String queryAll(Model model, DepartmentQueryObject qo) {
        PageInfo pageInfo = departmentService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "department/list"; // 逻辑视图 --> 物理视图 1 指定目录(后缀), 2 指定前缀
    }

    @RequestMapping("delete")
    @ResponseBody
    public PageResult delete(Long id) {
        departmentService.delete(id);
        return PageResult.success();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public PageResult delete(Department department) {
        if (department.getId() == null) {//新增操作
            departmentService.save(department);
        } else {//修改操作
            departmentService.update(department);
        }
        return PageResult.success();
    }
}

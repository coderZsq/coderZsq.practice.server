package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.qo.PageResult;
import com.coderZsq.rbac.qo.QueryObject;
import com.coderZsq.rbac.service.IDepartmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @RequestMapping("/list")
    @RequiresPermissions(value = {"department:list"})
    public String list(Model model, QueryObject qo) {
        PageResult<Department> result = service.selectByQuery(qo);
        /* 放到model里面的数据会共享到页面*/
        model.addAttribute("result", result);
        return "department/list";
    }


    @RequestMapping("/input")
    @RequiresPermissions(value = {"department:input"})
    public String input(Long id, Model model) {
        if (id != null){//编辑页面
            Department department = service.getById(id);
            model.addAttribute("entity",department);
        }
        return "department/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions(value = {"department:saveOrUpdate"})
    public  String saveOrUpdate(Department department){
        service.saveOrUpdate(department);
        return  "redirect:/department/list.do";
    }

    @RequestMapping("/delete")
    @RequiresPermissions(value = {"department:delete"})
    public String delete(Long id){
        service.delete(id);
        return  "redirect:/department/list.do";
    }
}

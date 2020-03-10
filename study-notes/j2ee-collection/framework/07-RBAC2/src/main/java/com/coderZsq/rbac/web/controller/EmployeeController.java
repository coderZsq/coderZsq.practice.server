package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.qo.EmployeeQueryObject;
import com.coderZsq.rbac.qo.PageResult;
import com.coderZsq.rbac.service.IDepartmentService;
import com.coderZsq.rbac.service.IEmployeeService;
import com.coderZsq.rbac.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService service;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;


    @RequestMapping("/list")
    @RequiresPermissions(value = {"employee:list"})
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        PageResult<Employee> result = service.selectByQuery(qo);
        model.addAttribute("result", result);
        model.addAttribute("depts",departmentService.getAllList());
        return "employee/list";
    }


    @RequestMapping("/input")
    @RequiresPermissions(value = {"employee:input"})
    public String input(Long id, Model model) {
        if (id != null){//编辑页面
            Employee employee = service.getById(id);
            model.addAttribute("entity",employee);
        }
        //所有部门
        model.addAttribute("depts",departmentService.getAllList());
        model.addAttribute("roles",roleService.getAllList());
        return "employee/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions(value = {"employee:saveOrUpdate"})
    public  String saveOrUpdate(Employee employee, Long[] roleIds){
        service.saveOrUpdate(employee,roleIds);
        return  "redirect:/employee/list.do";
    }

    @RequestMapping("/delete")
    @RequiresPermissions(value = {"employee:delete"})
    public String delete(Long id){
        service.delete(id);
        return  "redirect:/employee/list.do";
    }
}

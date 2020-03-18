package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.service.DepartmentService;
import com.coderZsq.crm.service.EmployeeService;
import com.coderZsq.crm.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.query.EmployeeQueryObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2020-03-18 14:32:13
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleService roleService;
	
    @RequestMapping("/list")
    public String list(Model model, EmployeeQueryObject qo){
        // 把所有的部门查询共享到页面
        model.addAttribute("depts", departmentService.queryAll());
        model.addAttribute("pageInfo",employeeService.query(qo));
        model.addAttribute("qo",qo);
        return "employee/list";
    }
	
	@RequestMapping("/input")
    public String input(Model model,Long id){
        // 查询所有的部门信息
        model.addAttribute("depts", departmentService.queryAll());
        // 查询所有的角色信息
        model.addAttribute("roles", roleService.queryAll());
        if(id!=null){
            Employee employee = employeeService.queryById(id);
            model.addAttribute("employee",employee);
        }
        return "employee/input";
    }
	
	
	@RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee, Long[] ids){
        if(employee!=null&& employee.getId()!=null){
            employeeService.update(employee, ids);
        }else{
            employeeService.insert(employee, ids);
        }
        // 重定向到列表页面的控制器
        return "redirect:/employee/list.do";
    }
	
	@RequestMapping("/delete")
	@ResponseBody
    public PageResult delete(Long id){
        if(id!=null){
            employeeService.deleteById(id);
        }
        return PageResult.success();
    }
}
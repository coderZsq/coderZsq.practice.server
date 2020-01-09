package com.coderZsq.rbac.web.controller;

import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.query.EmployeeQueryObject;
import com.coderZsq.rbac.service.IEmployeeService;
import com.coderZsq.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/query")
    public PageResult<PageInfo<Employee>> query(EmployeeQueryObject queryObject){
        PageInfo<Employee> pageInfo = employeeService.query(queryObject);
        return PageResult.success(pageInfo);
    }

    @RequestMapping("/get")
    public PageResult<Employee> get(Long id){
        Employee employee = employeeService.get(id);
        return PageResult.success(employee);
    }

    @RequestMapping("/saveOrUpdate")
    public PageResult saveOrUpdate(Employee employee,Long[] ids){
        Long id = employee.getId();
        if(id!=null){
            employeeService.update(employee,ids);
        }else{
            employeeService.insert(employee,ids);
        }
        return PageResult.success(true);
    }

    @RequestMapping("/batchDelete")
    public PageResult batchDelete(Long[] ids){
        if(ids!=null && ids.length>0){
            employeeService.batchDelete(ids);
        }
        return  PageResult.success(true);
    }
}

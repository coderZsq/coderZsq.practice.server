package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.Customer;
import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.service.CustomerService;
import com.coderZsq.crm.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.query.CustomerQueryObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Customer)表控制层
 *
 * @author makejava
 * @since 2020-03-27 13:47:11
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    /**
     * 服务对象
     */
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;
	
    @RequestMapping("/list")
    public String list(Model model, CustomerQueryObject qo){
        // 查询数据的时候, 需要判断除了管理员或市场经理, 其他用户必须只能看自己的数据
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (!employee.isAdmin() || SecurityUtils.getSubject().hasRole("Market_Manager")) {
            qo.setSellerId(employee.getId());
        }
        model.addAttribute("pageInfo",customerService.query(qo));
        model.addAttribute("qo",qo);
        // 共享销售人员的列表信息 销售人员 --> 角色为 市场专员或者是市场经理
        model.addAttribute("sellers", employeeService.querySellers());
        return "customer/list";
    }
	
	@RequestMapping("/input")
    public String input(Model model,Long id){
        if(id!=null){
            Customer customer = customerService.queryById(id);
            model.addAttribute("customer",customer);
        }
        return "customer/input";
    }
	
	
	@RequestMapping("/saveOrUpdate")
    @ResponseBody
    public PageResult saveOrUpdate(Customer customer){
        if(customer!=null&& customer.getId()!=null){
            customerService.update(customer);
        }else{
            customerService.insert(customer);
        }
        return PageResult.success();
    }
	
	@RequestMapping("/delete")
	@ResponseBody
    public PageResult delete(Long id){
        if(id!=null){
            customerService.deleteById(id);
        }
        return PageResult.success();
    }
}
package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.domain.Customer;
import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.domain.SystemDictionary;
import com.coderZsq.crm.query.CustomerQueryObject;
import com.coderZsq.crm.service.CustomerService;
import com.coderZsq.crm.service.EmployeeService;
import com.coderZsq.crm.service.SystemDictionaryItemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * (Customer)表控制层
 *
 * @author makejava
 * @since 2020-03-26 21:45:35
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

    @Autowired
    private SystemDictionaryItemService systemDictionaryItemService;

    /**
     * 查询所有的客户台账
     *
     * @param model
     * @param qo
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, CustomerQueryObject qo) {
        //查询数据的时候, 需要判断除了管理员或市场经理, 其他用户必须只能看自己的数据
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (!(employee.isAdmin() || SecurityUtils.getSubject().hasRole("Market_Manager"))) {
            qo.setSellerId(employee.getId());
        }
        model.addAttribute("pageInfo", customerService.query(qo));
        model.addAttribute("qo", qo);
        //共享销售人员的列表信息 销售人员--> 角色为 市场专员或者是市场经理
        model.addAttribute("sellers", employeeService.querySellers());
        return "customer/list";
    }

    /**
     * 显示潜在客户列表
     *
     * @param model
     * @param qo
     * @return
     */
    @RequestMapping("/potentialList")
    public String potentialList(Model model, CustomerQueryObject qo) {
        //查询数据的时候, 需要判断除了管理员或市场经理, 其他用户必须只能看自己的数据
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (!(employee.isAdmin() || SecurityUtils.getSubject().hasRole("Market_Manager"))) {
            qo.setSellerId(employee.getId());
        }
        qo.setStatus(Customer.STATUS_POTENTIAL);
        model.addAttribute("pageInfo", customerService.query(qo));
        model.addAttribute("qo", qo);
        //共享销售人员的列表信息 销售人员--> 角色为 市场专员或者是市场经理
        model.addAttribute("sellers", employeeService.querySellers());
        //根据字典类别id查询所有的明细数据
        //查询工作
        List<SystemDictionary> items = systemDictionaryItemService.queryByParentId(1L);
        model.addAttribute("jobs", items);
        //客户来源
        items = systemDictionaryItemService.queryByParentId(16L);
        model.addAttribute("sources", items);
        //交流方式
        items = systemDictionaryItemService.queryByParentId(26L);
        model.addAttribute("ccts", items);
        return "customer/potential_list";
    }

    /**
     * 客户资源池的额数据
     *
     * @param model
     * @return
     */
    @RequestMapping("/poolList")
    public String poolList(Model model, CustomerQueryObject qo) {
        //查询数据的时候, 需要判断除了管理员或市场经理, 其他用户必须只能看自己的数据
        qo.setStatus(Customer.STATUS_FAIL);
        model.addAttribute("pageInfo", customerService.query(qo));
        model.addAttribute("qo", qo);
        //共享销售人员的列表信息 销售人员--> 角色为 市场专员或者是市场经理
        model.addAttribute("sellers", employeeService.querySellers());
        return "customer/pool_list";
    }

    /**
     * 失败客户
     *
     * @param model
     * @return
     */
    @RequestMapping("/failList")
    public String failList(Model model, CustomerQueryObject qo) {
        //查询数据的时候, 需要判断除了管理员或市场经理, 其他用户必须只能看自己的数据
        qo.setStatus(Customer.STATUS_FORMAL);
        model.addAttribute("pageInfo", customerService.query(qo));
        model.addAttribute("qo", qo);
        //共享销售人员的列表信息 销售人员--> 角色为 市场专员或者是市场经理
        model.addAttribute("sellers", employeeService.querySellers());
        return "customer/fail_list";
    }


    @RequestMapping("/input")
    public String input(Model model, Long id) {
        if (id != null) {
            Customer customer = customerService.queryById(id);
            model.addAttribute("customer", customer);
        }
        return "customer/input";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public PageResult saveOrUpdate(Customer customer) {
        if (customer != null && customer.getId() != null) {
            customerService.update(customer);
        } else {
            customerService.insert(customer);
        }
        return PageResult.success();
    }

    /**
     * 修改单据状态
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public PageResult delete(Long id) {
        if (id != null) {
            customerService.deleteById(id);
        }
        return PageResult.success();
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public PageResult updateStatus(Long customerId, Integer status) {
        customerService.updateStatus(customerId, status);
        return PageResult.success();
    }
}
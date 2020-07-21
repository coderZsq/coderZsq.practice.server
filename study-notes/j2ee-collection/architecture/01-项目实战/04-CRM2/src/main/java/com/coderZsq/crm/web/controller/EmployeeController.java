package com.coderZsq.crm.web.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.domain.Role;
import com.coderZsq.crm.query.EmployeeQueryObject;
import com.coderZsq.crm.service.DepartmentService;
import com.coderZsq.crm.service.EmployeeService;
import com.coderZsq.crm.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2020-03-18 14:32:13
 */
@Controller
@RequestMapping("employee")
@Slf4j
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
    @RequiresPermissions(value = {"员工列表", "employee:query"}, logical = Logical.OR)
    public String list(Model model, EmployeeQueryObject qo) {
        // 把所有的部门查询共享到页面
        model.addAttribute("depts", departmentService.queryAll());
        model.addAttribute("pageInfo", employeeService.query(qo));
        model.addAttribute("qo", qo);
        return "employee/list";
    }

    @RequestMapping("/input")
    public String input(Model model, Long id) {
        // 查询所有的部门信息
        model.addAttribute("depts", departmentService.queryAll());
        // 查询所有的角色信息
        model.addAttribute("roles", roleService.queryAll());
        if (id != null) {
            Employee employee = employeeService.queryById(id);
            model.addAttribute("employee", employee);
        }
        return "employee/input";
    }


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee, Long[] ids) {
        if (employee != null && employee.getId() != null) {
            employeeService.update(employee, ids);
        } else {
            employeeService.insert(employee, ids);
        }
        // 重定向到列表页面的控制器
        return "redirect:/employee/list.do";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public PageResult delete(Long id) {
        if (id != null) {
            employeeService.deleteById(id);
        }
        return PageResult.success();
    }

    // 批量删除
    @RequestMapping("/batchDelete")
    @ResponseBody
    public PageResult batchDelete(Long[] ids) {
        if (ids != null) {
            employeeService.batchDelete(ids);
        }
        return PageResult.success();
    }

    // 员工数据导出
    @RequestMapping("/exportXls")
    public void exportXls(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置下载的文件协议
            response.setHeader("Content-disposition", "attachment;filename=employee.xls");
            // 从数据库查询所有的数据出来
            List<Employee> employees = employeeService.queryAll();
            // 关联角色数据
            for (Employee employee : employees) {
                List<Role> roles = roleService.queryByEmpId(employee.getId());
                employee.setRoles(roles);
            }
            // 第一个参数: 设置表格的基本参数
            // 第二个参数: 设置数据类型pojo
            // 第三个参数: 数据集
            Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams("员工数据", "第一个表格"), Employee.class, employees);
            // 把数据响应到浏览器
            sheets.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 员工数据导入
    @RequestMapping("importXls")
    @ResponseBody
    public PageResult importXls(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            // 设置标题的行数
            params.setTitleRows(1);
            // 设置头占用的行数
            params.setHeadRows(2); // header占用的行数
            List<Employee> employees = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            // 遍历员工列表
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(0);
                // 设置员工的默认密码
                employee.setPassword("1");
                // 1 查询部门信息
                Department department = departmentService.queryByName(employee.getDept().getName());
                employee.setDept(department);
                List<Role> roles = employee.getRoles();
                Long[] roleIds = new Long[roles.size()];
                for (int j = 0; j < roles.size(); j++) {
                    // 2 查询员工的角色信息
                    Role role = roles.get(i);
                    Long roleId = roleService.queryBySn(role.getSn());
                    roleIds[j] = roleId;
                }
                // 3 保存到数据库
                employeeService.insert(employee, roleIds);
            }
            log.info("导入的员工数据: [{}]", employees);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return PageResult.success();
    }
}
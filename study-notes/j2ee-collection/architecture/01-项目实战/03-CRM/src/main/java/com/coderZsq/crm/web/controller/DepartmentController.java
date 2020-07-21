package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.query.DepartmentQueryObject;
import com.coderZsq.crm.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("department")
@Slf4j
public class DepartmentController {
    // private static Logger log = LoggerFactory.getLogger("DepartmentController");

    // 根据类型去匹配对应的bean对象
    @Autowired // --> 可能会找到多个bean对象
    @Qualifier("departmentServiceImpl")
    private DepartmentService departmentService;

    @RequestMapping("list")
    @RequiresPermissions(value = {"部门列表", "department:query"}, logical = Logical.OR) // 是用来判断权限
    public String query(Model model, DepartmentQueryObject qo) {
        log.info("请求参数: [{}]", qo);
        PageInfo pageInfo = departmentService.query(qo);
        log.info("响应数据: [{}]", pageInfo.getList());
        // 模拟异常
        // int i = 1 / 0;
        model.addAttribute("pageInfo", pageInfo);
        return "department/list"; // 逻辑视图 --> 物理视图 1 指定目录(后缀), 2 指定前缀
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions(value = {"部门删除", "department:delete"}, logical = Logical.OR) // 是用来判断权限
    public PageResult delete(Long id) {
        departmentService.delete(id);
        // 模拟异常
        // int i = 1 / 0;
        return PageResult.success();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions(value = {"部门保存", "department:saveOrUpdate"}, logical = Logical.OR) // 是用来判断权限
    public PageResult delete(Department department) {
        if (department.getId() == null) {//新增操作
            departmentService.save(department);
        } else {//修改操作
            departmentService.update(department);
        }
        return PageResult.success();
    }
}

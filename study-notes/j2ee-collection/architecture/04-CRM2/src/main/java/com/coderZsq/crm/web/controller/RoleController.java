package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.Permission;
import com.coderZsq.crm.domain.Role;
import com.coderZsq.crm.service.PermissionService;
import com.coderZsq.crm.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.query.RoleQueryObject;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2020-03-18 15:50:37
 */
@Controller
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    public String list(Model model, RoleQueryObject qo){
        model.addAttribute("pageInfo",roleService.query(qo));
        model.addAttribute("qo",qo);
        return "role/list";
    }

	@RequestMapping("/input")
    public String input(Model model,Long id){
        // 查询所有的权限信息
        List<Permission> permissions = permissionService.queryAll();
        model.addAttribute("permissions", permissions);
        if(id!=null){
            Role role = roleService.queryById(id);
            model.addAttribute("role",role);
        }
        return "role/input";
    }


	@RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role, Long[] ids){
        if(role!=null&& role.getId()!=null){
            roleService.update(role, ids);
        }else{
            roleService.insert(role, ids);
        }
        // 重定向到列表页面的控制器
        return "redirect:/role/list.do";
    }

	@RequestMapping("/delete")
	@ResponseBody
    public PageResult delete(Long id){
        if(id!=null){
            roleService.deleteById(id);
        }
        return PageResult.success();
    }
}
package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.Permission;
import com.coderZsq.crm.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.query.PermissionQueryObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Permission)表控制层
 *
 * @author makejava
 * @since 2020-03-20 14:10:40
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Autowired
    private PermissionService permissionService;
	
	
    @RequestMapping("/list")
    public String list(Model model, PermissionQueryObject qo){
        model.addAttribute("pageInfo",permissionService.query(qo));
        model.addAttribute("qo",qo);
        return "permission/list";
    }
	
	@RequestMapping("/input")
    public String input(Model model,Long id){
        if(id!=null){
            Permission permission = permissionService.queryById(id);
            model.addAttribute("permission",permission);
        }
        return "permission/input";
    }
	
	
	@RequestMapping("/saveOrUpdate")
    public PageResult saveOrUpdate(Permission permission){
        if(permission!=null&& permission.getId()!=null){
            permissionService.update(permission);
        }else{
            permissionService.insert(permission);
        }
        return PageResult.success();
    }
	
	@RequestMapping("/delete")
	@ResponseBody
    // 通过注解的方式标记该方法需要权限控制
    // 只是需要全新控制 --> 给他分配对应的权限数据 --> 扫描对应的哪些方法贴了标记权限的注解即可
    public PageResult delete(Long id){
        if(id!=null){
            permissionService.deleteById(id);
        }
        return PageResult.success();
    }

    // 添加一个加载权限的方法 --> 刷新权限数据
    @RequestMapping("/reload")
    @ResponseBody
    public PageResult reload() {
        permissionService.reload();
        return PageResult.success();
    }
}
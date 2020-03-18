package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.Role;
import com.coderZsq.crm.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.query.RoleQueryObject;

import org.springframework.beans.factory.annotation.Autowired;

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


    @RequestMapping("/list")
    public String list(Model model, RoleQueryObject qo){
        model.addAttribute("pageInfo",roleService.query(qo));
        model.addAttribute("qo",qo);
        return "role/list";
    }

	@RequestMapping("/input")
    public String input(Model model,Long id){
        System.out.println(11111);
        if(id!=null){
            Role role = roleService.queryById(id);
            model.addAttribute("role",role);
        }
        return "role/input";
    }


	@RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role){
        if(role!=null&& role.getId()!=null){
            roleService.update(role);
        }else{
            roleService.insert(role);
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
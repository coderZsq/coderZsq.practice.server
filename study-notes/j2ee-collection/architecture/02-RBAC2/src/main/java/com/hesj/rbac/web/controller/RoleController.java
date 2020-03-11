package com.hesj.rbac.web.controller;

import com.hesj.rbac.domain.Role;
import com.hesj.rbac.qo.PageResult;
import com.hesj.rbac.qo.QueryObject;
import com.hesj.rbac.service.IPermissionService;
import com.hesj.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService service;

    @Autowired
    private IPermissionService permissionService;


    @RequestMapping("/list")
    public String list(Model model, QueryObject qo) {
        PageResult<Role> result = service.selectByQuery(qo);
        model.addAttribute("result", result);
        return "role/list";
    }


    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) {//编辑页面
            Role role = service.getById(id);
            model.addAttribute("entity", role);

            //根据角色id查询权限
            //List<Permission> permissions = permissionService.getAllPermissionByRoleId(role.getId());
            //model.addAttribute("selfPermissions",permissions);
        }

        //权限
        model.addAttribute("permissions", permissionService.getAllList());
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role, Long[] permissionIds) {
        service.saveOrUpdate(role, permissionIds);
        return "redirect:/role/list.do";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        service.delete(id);
        return "redirect:/role/list.do";
    }

}

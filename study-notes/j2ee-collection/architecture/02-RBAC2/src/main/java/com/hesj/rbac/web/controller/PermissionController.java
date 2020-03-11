package com.hesj.rbac.web.controller;

import com.hesj.rbac.domain.Permission;
import com.hesj.rbac.qo.PageResult;
import com.hesj.rbac.qo.QueryObject;
import com.hesj.rbac.service.IPermissionService;
import com.hesj.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService service;

    @RequestMapping("/list")
    @RequiredPermission({"权限列表","permission:list"})
    public String list(Model model, QueryObject qo) {
        PageResult<Permission> result = service.selectByQuery(qo);
        model.addAttribute("result", result);
        return "permission/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"权限删除","permission:delete"})
    public String delete(Long id){
        service.delete(id);
        return  "redirect:/permission/list.do";
    }

    @RequestMapping("/reload")
    public String reload(){
        //将资源的权限全部存放到数据库
        service.reload();
        return "redirect:/permission/list.do";//重新查询出来
    }


}

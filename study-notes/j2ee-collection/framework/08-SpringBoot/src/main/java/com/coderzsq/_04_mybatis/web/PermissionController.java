package com.coderzsq._04_mybatis.web;

import com.coderzsq._04_mybatis.domain.Permission;
import com.coderzsq._04_mybatis.service.PremissionService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PermissionController {
    @Autowired
    private PremissionService premissionService;

    @RequestMapping("list")
    public List<Permission> list() {
        List<Permission> list = premissionService.list();
        return list;
    }

    @RequestMapping("save")
    public String save(Permission permission) {
        premissionService.save(permission);
        return "success";
    }
}

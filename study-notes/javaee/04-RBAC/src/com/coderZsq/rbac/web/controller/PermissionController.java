package com.coderZsq.rbac.web.controller;

import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Permission;
import com.coderZsq.rbac.query.QueryObject;
import com.coderZsq.rbac.service.IPermissionService;
import com.coderZsq.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("permission")
public class PermissionController {


    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("query")
    public PageResult<PageInfo<Permission>> query(QueryObject queryObject){
        return PageResult.success(permissionService.query(queryObject));
    }
    @RequestMapping("queryAll")
    public PageResult<List<Permission>> queryAll(){
        return PageResult.success(permissionService.queryAll());
    }

    @RequestMapping("delete")
    public PageResult delete(Long id){
        permissionService.delete(id);
        return PageResult.success(true);
    }

    @RequestMapping("queryByRoleId")
    public PageResult<List<Permission>> queryByRoleId(Long roleId){
        return PageResult.success(permissionService.queryByRoleId(roleId));
    }

    @RequestMapping("reload")
    public PageResult<Boolean> reload(){
        permissionService.reload();
        return PageResult.success(true);
    }
}

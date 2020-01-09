package com.coderZsq.rbac.web.controller;

import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Role;
import com.coderZsq.rbac.query.QueryObject;
import com.coderZsq.rbac.service.IRoleService;
import com.coderZsq.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/query")
    public PageResult<PageInfo<Role>> query(QueryObject qo){
        PageInfo<Role> pageInfo = roleService.query(qo);
        PageResult result = PageResult.success(pageInfo);
        return result;
    }

    @RequestMapping("/get")
    public PageResult<Role> get(Long id){
        Role role = roleService.get(id);
        return PageResult.success(role);
    }

    @RequestMapping("/queryAll")
    public PageResult<List<Role>> queryAll(){
        return PageResult.success(roleService.queryAll());
    }


    @RequestMapping("/queryByEmpId")
    public PageResult<List<Role>> queryByEmpId(Long empId){
        return PageResult.success(roleService.queryByEmpId(empId));
    }

    @RequestMapping("/saveOrUpdate")
    public PageResult saveOrUpdate(Role role,Long[] ids){
        Long id = role.getId();
        if(id==null){
            roleService.insert(role,ids);
        }else{
            roleService.update(role,ids);
        }
        return PageResult.success(true);
    }

    @RequestMapping("/delete")
    public PageResult delete(Long id){
        if(id!=null){
            roleService.delete(id);
        }
        return PageResult.success(true);
    }
}

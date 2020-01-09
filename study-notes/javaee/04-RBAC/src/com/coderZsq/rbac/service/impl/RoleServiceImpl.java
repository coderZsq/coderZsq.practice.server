package com.coderZsq.rbac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Role;
import com.coderZsq.rbac.mapper.RoleMapper;
import com.coderZsq.rbac.query.QueryObject;
import com.coderZsq.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Role role,Long[] ids) {
        //1 先保存角色信息
        roleMapper.insert(role);
        //2 在保存角色和权限的关联关系
        if(ids!=null && ids.length>0){
            for (Long permissionId : ids) {
                roleMapper.insertRelation(role.getId(),permissionId);
            }
        }
    }

    @Override
    public void update(Role role,Long[] ids) {
        roleMapper.deleteRelation(role.getId());
        roleMapper.updateByPrimaryKey(role);
        if(ids!=null && ids.length>0){
            for (Long permissionId : ids) {
                roleMapper.insertRelation(role.getId(), permissionId);
            }
        }
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> queryAll() {
        return roleMapper.selectAll();
    }

    @Override
    public PageInfo<Role> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> roles = roleMapper.query(qo);
        return new PageInfo<>(roles);
    }

    @Override
    public List<Role> queryByEmpId(Long empId) {
        return roleMapper.queryByEmpId(empId);
    }
}

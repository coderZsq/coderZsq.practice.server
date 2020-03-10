package com.coderZsq.rbac.service.impl;

import com.coderZsq.rbac.domain.Role;
import com.coderZsq.rbac.mapper.RoleMapper;
import com.coderZsq.rbac.qo.PageResult;
import com.coderZsq.rbac.qo.QueryObject;
import com.coderZsq.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper mapper;//NullPointException

    @Override
    public void saveOrUpdate(Role role, Long[] permissionIds) {
        if (role.getId() == null) {
            mapper.insert(role);

        } else {
            mapper.updateByPrimaryKey(role);
            //先删除,后添加
            mapper.deleteRelationRoleAndPermissionByRoleId(role.getId());
        }

        if (permissionIds != null){
            for (Long permissionId : permissionIds) {
                mapper.insertRelationRoleAndPermission(role.getId(),permissionId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Role getById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> getAllList() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Role> selectByQuery(QueryObject qo) {
        //判断查询条数
        int count = mapper.selectByCount();
        if (count == 0){
            return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),0, Collections.emptyList());
        }

        List<Role> roles = mapper.selectByQuery(qo);
        return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),count, roles);
    }
}

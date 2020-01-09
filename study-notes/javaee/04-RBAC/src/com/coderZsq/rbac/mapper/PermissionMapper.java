package com.coderZsq.rbac.mapper;

import com.coderZsq.rbac.domain.Permission;
import com.coderZsq.rbac.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission>  query(QueryObject queryObject);

    List<Permission> queryByRoleId(Long roleId);
}
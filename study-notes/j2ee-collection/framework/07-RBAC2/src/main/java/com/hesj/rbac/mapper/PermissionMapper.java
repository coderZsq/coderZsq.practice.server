package com.hesj.rbac.mapper;

import com.hesj.rbac.domain.Permission;
import com.hesj.rbac.qo.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByQuery(QueryObject qo);

    int selectByCount();

    List<String> selectAllExpressions();

    List<Permission> selectAllPermissionByRoleId(Long roleId);

    List<String> selectAllExpressionsByEmployeeId(Long employeeId);


    List<String> queryByEmployeeId(Long employeeId);
}
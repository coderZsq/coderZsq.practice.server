package com.hesj.rbac.mapper;

import com.hesj.rbac.domain.Role;
import com.hesj.rbac.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectByQuery(QueryObject qo);

    int selectByCount();

    void insertRelationRoleAndPermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRelationRoleAndPermissionByRoleId(Long roleId);


    List<String> queryRoleSnsByEmpId(Long employeeId);
}
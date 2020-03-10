package com.coderZsq.rbac.mapper;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.qo.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
    
    List<Employee> selectByQuery(EmployeeQueryObject qo);

    int selectByCount(EmployeeQueryObject qo);

    void insertRelationEmployeeAndRole(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);

    void deleteRelationEmployeeAndRole(Long id);

    Employee selectByNameAndPassword(@Param("username") String username, @Param("password") String password);

    Employee queryByName(String username);
}
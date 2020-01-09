package com.coderZsq.rbac.mapper;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入员工信息
     * @param record
     * @return
     */
    int insert(Employee record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Employee selectByPrimaryKey(Long id);

    /**
     * 查询所有的员工信息
     * @return
     */
    List<Employee> selectAll();

    /**
     * 根据主键修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Employee record);

    List<Employee> query(EmployeeQueryObject queryObject);

    void insertRelation(@Param("empId") Long empId, @Param("roleId") Long roleId);

    void deleteRelation(Long empId);

    Employee selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    void batchDelete(Long[] ids);
}
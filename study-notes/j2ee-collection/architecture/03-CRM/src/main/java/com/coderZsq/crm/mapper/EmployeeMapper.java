package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.Employee;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Employee)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-18 14:32:13
 */
public interface EmployeeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Employee queryById(Long id);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<Employee> queryAll();
	
	/**
     * 根据查询条件查询指定列表数据
     * @return 对象列表
     */
    List<Employee> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int insert(Employee employee);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int update(Employee employee);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    void insertRelation(@Param("empId") Long empId, @Param("roleId") Long roleId);

    void deleteRelation(Long empId);

    Employee queryByUsername(String username);
}
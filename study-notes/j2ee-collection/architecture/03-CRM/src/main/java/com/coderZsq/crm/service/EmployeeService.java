package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.Employee;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Employee)表服务接口
 *
 * @author makejava
 * @since 2020-03-18 14:32:13
 */
public interface EmployeeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Employee queryById(Long id);

    /**
     * 查询所有数据
     * @return 对象列表
     */
    List<Employee> queryAll();
	
    /**
     * 查询指定列表数据
     * @return 对象列表
     */
    PageInfo<Employee> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee insert(Employee employee, Long[] ids);

    /**
     * 修改数据
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee update(Employee employee, Long[] ids);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
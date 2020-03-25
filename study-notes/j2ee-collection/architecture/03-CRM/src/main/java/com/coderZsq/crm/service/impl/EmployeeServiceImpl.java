package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.domain.Role;
import com.coderZsq.crm.mapper.EmployeeMapper;
import com.coderZsq.crm.mapper.RoleMapper;
import com.coderZsq.crm.query.QueryObject;
import com.coderZsq.crm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Employee)表服务实现类
 *
 * @author makejava
 * @since 2020-03-18 14:32:13
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Employee queryById(Long id) {
        Employee employee = this.employeeMapper.queryById(id);
        // 自己去查询员工的角色信息
        List<Role> roles = roleMapper.queryByEmpId(id);
        employee.setRoles(roles);
        return employee;
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Employee> queryAll() {
        return this.employeeMapper.queryAll();
    }

    /**
     * 根据条件查询数据
     *
     * @return 对象列表
     */
    @Override
    public PageInfo<Employee> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Employee> list = employeeMapper.query(qo);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee, Long[] ids) {
        // 0 对员工用户的密码进行加密操作
        employee.setPassword(new Md5Hash(employee.getPassword()).toString());
        // 1 先保存员工信息, 才会生成员工id
        this.employeeMapper.insert(employee);
        // 2 保存关联关系
        if (ids != null) {
            for (Long roleId : ids) {
                this.employeeMapper.insertRelation(employee.getId(), roleId);
            }
        }
        return employee;
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee update(Employee employee, Long[] ids) {
        // 1 先删除原来的中间表的关系
        this.employeeMapper.deleteRelation(employee.getId());
        this.employeeMapper.update(employee);
        // 3 插入新的关系
        if (ids != null) {
            for (Long roleId : ids) {
                this.employeeMapper.insertRelation(employee.getId(), roleId);
            }
        }
        return this.queryById(employee.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        // 1 先删除关联关系
        this.employeeMapper.deleteRelation(id);
        // 2 删除员工数据
        boolean flag = this.employeeMapper.deleteById(id) > 0;
        return flag;
    }

    @Override
    public void batchDelete(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}
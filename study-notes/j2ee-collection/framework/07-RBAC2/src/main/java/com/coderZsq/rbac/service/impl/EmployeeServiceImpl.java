package com.coderZsq.rbac.service.impl;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.mapper.EmployeeMapper;
import com.coderZsq.rbac.qo.EmployeeQueryObject;
import com.coderZsq.rbac.qo.PageResult;
import com.coderZsq.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper mapper;//NullPointException

    @Override
    public void saveOrUpdate(Employee employee, Long[] roleIds) {
        if (employee.getId() == null) {
            mapper.insert(employee);
        } else {
            mapper.updateByPrimaryKey(employee);
            //先删除根据employee_id删除中间表的关联关系
            mapper.deleteRelationEmployeeAndRole(employee.getId());
        }

        //角色关联表的添加  员工id 和 角色id
        if (roleIds != null){//有角色的时候才需要保存关联
            for (Long roleId : roleIds) {
                mapper.insertRelationEmployeeAndRole(employee.getId(),roleId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            mapper.deleteByPrimaryKey(id);

            //根据employee_id删除
            mapper.deleteRelationEmployeeAndRole(id);
        }
    }

    @Override
    public Employee getById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> getAllList() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Employee> selectByQuery(EmployeeQueryObject qo) {
        //判断查询条数
        int count = mapper.selectByCount(qo);
        if (count == 0){
            return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),0, Collections.emptyList());
        }

        List<Employee> employees = mapper.selectByQuery(qo);
        return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),count, employees);
    }

    @Override
    public Employee selectByNameAndPassword(String username, String password) {
        return mapper.selectByNameAndPassword(username,password);
    }

}

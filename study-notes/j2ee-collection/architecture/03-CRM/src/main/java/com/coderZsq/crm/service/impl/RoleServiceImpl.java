package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.Role;
import com.coderZsq.crm.mapper.RoleMapper;
import com.coderZsq.crm.service.RoleService;
import org.springframework.stereotype.Service;
import com.coderZsq.crm.query.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2020-03-18 15:50:36
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Long id) {
        return this.roleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<Role> queryAll() {
        return this.roleMapper.queryAll();
    }
	
    /**
     * 根据条件查询数据
     * @return 对象列表
     */
	@Override
    public PageInfo<Role> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Role> list = roleMapper.query(qo);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleMapper.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleMapper.update(role);
        return this.queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.roleMapper.deleteById(id) > 0;
    }
}
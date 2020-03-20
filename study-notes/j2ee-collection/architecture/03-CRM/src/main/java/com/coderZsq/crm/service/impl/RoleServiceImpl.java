package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.Permission;
import com.coderZsq.crm.domain.Role;
import com.coderZsq.crm.mapper.PermissionMapper;
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

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Long id) {
        Role role = this.roleMapper.queryById(id);
        // 手动维护权限信息
        List<Permission> permissions = permissionMapper.queryByRoleId(id);
        role.setPermissions(permissions);
        return role;
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
    public Role insert(Role role, Long[] ids) {
        // 1 插入角色信息
        this.roleMapper.insert(role);
        // 2 插入中间关联表
        if (ids != null) {
            for (Long permissionId : ids) {
                this.roleMapper.insertRelation(role.getId(), permissionId);
            }
        }
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role, Long[] ids) {
        // 1 删除原来中间表的数据
        this.roleMapper.deleteRelation(role.getId());
        // 2 修改角色信息
        this.roleMapper.update(role);
        // 3 插入新的中间表信息
        if (ids != null) {
            for (Long permissionId : ids) {
                this.roleMapper.insertRelation(role.getId(), permissionId);
            }
        }
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
        this.roleMapper.deleteRelation(id);
        return this.roleMapper.deleteById(id) > 0;
    }
}
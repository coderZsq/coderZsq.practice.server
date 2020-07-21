package com.coderZsq.rbac.service;

import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Role;
import com.coderZsq.rbac.query.QueryObject;

import java.util.List;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
public interface IRoleService {

    /**
     * 根据id删除指定角色
     * @param id
     */
    public void delete(Long id);

    /**
     * 新增角色
     * @param role
     */
    public void insert(Role role,Long[] ids);

    /**
     * 根据角色id修改角色
     * @param role
     */
    public void update(Role role,Long[] ids);

    /**
     * 根据角色Id获取角色
     * @param id
     * @return
     */
    public Role get(Long id);

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> queryAll();

    /**
     * 分页查询
     * @return
     */
    public PageInfo<Role> query(QueryObject qo);

    List<Role> queryByEmpId(Long empId);
}

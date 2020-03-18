package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.Role;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2020-03-18 15:50:35
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Long id);

    /**
     * 查询所有数据
     * @return 对象列表
     */
    List<Role> queryAll();
	
    /**
     * 查询指定列表数据
     * @return 对象列表
     */
    PageInfo<Role> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
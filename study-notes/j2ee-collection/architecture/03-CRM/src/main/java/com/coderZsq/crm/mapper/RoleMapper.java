package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-18 15:50:34
 */
public interface RoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Long id);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<Role> queryAll();
	
	/**
     * 根据查询条件查询指定列表数据
     * @return 对象列表
     */
    List<Role> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<Role> queryByEmpId(Long empId);

    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRelation(Long roleId);

    List<String> querySnByEmpId(Long id);
}
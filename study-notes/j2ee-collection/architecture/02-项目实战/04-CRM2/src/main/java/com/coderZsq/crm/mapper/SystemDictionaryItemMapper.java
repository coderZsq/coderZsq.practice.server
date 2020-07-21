package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.SystemDictionary;
import com.coderZsq.crm.domain.SystemDictionaryItem;

import java.util.List;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Systemdictionaryitem)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 11:56:09
 */
public interface SystemDictionaryItemMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemDictionaryItem queryById(Long id);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<SystemDictionaryItem> queryAll();
	
	/**
     * 根据查询条件查询指定列表数据
     * @return 对象列表
     */
    List<SystemDictionaryItem> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param systemdictionaryitem 实例对象
     * @return 影响行数
     */
    int insert(SystemDictionaryItem systemdictionaryitem);

    /**
     * 修改数据
     *
     * @param systemdictionaryitem 实例对象
     * @return 影响行数
     */
    int update(SystemDictionaryItem systemdictionaryitem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SystemDictionary> queryByParentId(long parentId);
}
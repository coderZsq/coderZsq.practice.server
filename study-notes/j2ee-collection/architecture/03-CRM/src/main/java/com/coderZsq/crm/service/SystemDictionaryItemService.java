package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.SystemDictionary;
import com.coderZsq.crm.domain.SystemDictionaryItem;
import com.coderZsq.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Systemdictionaryitem)表服务接口
 *
 * @author makejava
 * @since 2020-03-27 11:56:09
 */
public interface SystemDictionaryItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemDictionaryItem queryById(Long id);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SystemDictionaryItem> queryAll();

    /**
     * 查询指定列表数据
     *
     * @return 对象列表
     */
    PageInfo<SystemDictionaryItem> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param systemdictionaryitem 实例对象
     * @return 实例对象
     */
    SystemDictionaryItem insert(SystemDictionaryItem systemdictionaryitem);

    /**
     * 修改数据
     *
     * @param systemdictionaryitem 实例对象
     * @return 实例对象
     */
    SystemDictionaryItem update(SystemDictionaryItem systemdictionaryitem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据parentId查询对应的字典目录明细
     *
     * @param parentId
     * @return
     */
    List<SystemDictionary> queryByParentId(long parentId);
}
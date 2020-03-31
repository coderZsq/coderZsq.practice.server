package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.SystemDictionary;
import com.coderZsq.crm.domain.SystemDictionaryItem;
import com.coderZsq.crm.mapper.SystemDictionaryItemMapper;
import com.coderZsq.crm.service.SystemDictionaryItemService;
import org.springframework.stereotype.Service;
import com.coderZsq.crm.query.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Systemdictionaryitem)表服务实现类
 *
 * @author makejava
 * @since 2020-03-27 11:56:09
 */
@Service("systemDictionaryItemService")
public class SystemDictionaryItemServiceImpl implements SystemDictionaryItemService {

    @Autowired
    private SystemDictionaryItemMapper systemdictionaryitemMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SystemDictionaryItem queryById(Long id) {
        return this.systemdictionaryitemMapper.queryById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<SystemDictionaryItem> queryAll() {
        return this.systemdictionaryitemMapper.queryAll();
    }
	
    /**
     * 根据条件查询数据
     * @return 对象列表
     */
	@Override
    public PageInfo<SystemDictionaryItem> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<SystemDictionaryItem> list = systemdictionaryitemMapper.query(qo);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param systemdictionaryitem 实例对象
     * @return 实例对象
     */
    @Override
    public SystemDictionaryItem insert(SystemDictionaryItem systemdictionaryitem) {
        this.systemdictionaryitemMapper.insert(systemdictionaryitem);
        return systemdictionaryitem;
    }

    /**
     * 修改数据
     *
     * @param systemdictionaryitem 实例对象
     * @return 实例对象
     */
    @Override
    public SystemDictionaryItem update(SystemDictionaryItem systemdictionaryitem) {
        this.systemdictionaryitemMapper.update(systemdictionaryitem);
        return this.queryById(systemdictionaryitem.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.systemdictionaryitemMapper.deleteById(id) > 0;
    }

    @Override
    public List<SystemDictionary> queryByParentId(long parentId) {
        return systemdictionaryitemMapper.queryByParentId(parentId);
    }
}
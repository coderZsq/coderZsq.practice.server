package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.CustomerTraceHistory;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Customertracehistory)表服务接口
 *
 * @author makejava
 * @since 2020-03-31 17:08:38
 */
public interface CustomerTraceHistoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CustomerTraceHistory queryById(Long id);

    /**
     * 查询所有数据
     * @return 对象列表
     */
    List<CustomerTraceHistory> queryAll();
	
    /**
     * 查询指定列表数据
     * @return 对象列表
     */
    PageInfo<CustomerTraceHistory> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param customertracehistory 实例对象
     * @return 实例对象
     */
    CustomerTraceHistory insert(CustomerTraceHistory customertracehistory);

    /**
     * 修改数据
     * @param customertracehistory 实例对象
     * @return 实例对象
     */
    CustomerTraceHistory update(CustomerTraceHistory customertracehistory);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
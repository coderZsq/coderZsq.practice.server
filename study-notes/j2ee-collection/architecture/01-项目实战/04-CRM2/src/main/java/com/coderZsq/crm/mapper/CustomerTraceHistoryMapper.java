package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.CustomerTraceHistory;
import com.coderZsq.crm.query.QueryObject;

import java.util.List;

/**
 * (CustomerTraceHistory)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-29 20:54:49
 */
public interface CustomerTraceHistoryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CustomerTraceHistory queryById(Long id);

    /**
     * 查询所有行数据
     *
     * @return 对象列表
     */
    List<CustomerTraceHistory> queryAll();

    /**
     * 根据查询条件查询指定列表数据
     *
     * @return 对象列表
     */
    List<CustomerTraceHistory> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param customertracehistory 实例对象
     * @return 影响行数
     */
    int insert(CustomerTraceHistory customertracehistory);

    /**
     * 修改数据
     *
     * @param customertracehistory 实例对象
     * @return 影响行数
     */
    int update(CustomerTraceHistory customertracehistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
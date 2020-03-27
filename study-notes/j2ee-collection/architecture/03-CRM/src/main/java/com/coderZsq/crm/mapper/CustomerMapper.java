package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.Customer;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Customer)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 13:47:11
 */
public interface CustomerMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Customer queryById(Long id);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<Customer> queryAll();
	
	/**
     * 根据查询条件查询指定列表数据
     * @return 对象列表
     */
    List<Customer> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int insert(Customer customer);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
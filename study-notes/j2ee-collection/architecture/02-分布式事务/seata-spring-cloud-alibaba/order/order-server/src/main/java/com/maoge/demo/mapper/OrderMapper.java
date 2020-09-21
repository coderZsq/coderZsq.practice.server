package com.maoge.demo.mapper;

import com.maoge.demo.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    @Insert("insert into `order`(product_id,amount,sum,create_time,account_id) " +
            " values(#{productId},#{amount},#{sum},#{createTime},#{accountId})")
    public void createOrder(Order order);
}

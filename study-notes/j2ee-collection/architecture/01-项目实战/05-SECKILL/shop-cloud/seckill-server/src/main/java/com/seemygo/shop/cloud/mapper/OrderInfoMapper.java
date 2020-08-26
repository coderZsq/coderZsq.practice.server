package com.seemygo.shop.cloud.mapper;

import com.seemygo.shop.cloud.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderInfoMapper {
    @Insert("insert into t_order_info(order_no,user_id,good_id,delivery_addr_id,good_name,good_img,good_count,good_price,seckill_price,status,create_date,pay_date) values(#{orderNo},#{userId},#{goodId},#{deliveryAddrId},#{goodName},#{goodImg},#{goodCount},#{goodPrice},#{seckillPrice},#{status},#{createDate},#{payDate})")
    void insert(OrderInfo orderInfo);

    @Select("select * from t_order_info where order_no = #{orderNo} AND user_id = #{userId}")
    OrderInfo selectByPrimaryKey(@Param("orderNo") String orderNo, @Param("userId") Long userId);

    @Update("update t_order_info set status = 3 where order_no = #{orderNo} and status = 0")
    int updateTimeout(String orderNo);

    @Update("update t_order_info set status = 1, pay_date = now() where order_no = #{orderNo}")
    void updatePaySuccess(String orderNo);
}

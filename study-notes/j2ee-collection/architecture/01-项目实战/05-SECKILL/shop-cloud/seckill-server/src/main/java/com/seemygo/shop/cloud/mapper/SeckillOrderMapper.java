package com.seemygo.shop.cloud.mapper;

import com.seemygo.shop.cloud.domain.SeckillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SeckillOrderMapper {

    @Select("select * from t_seckill_order where user_id = #{userId} and seckill_id = #{seckillId}")
    SeckillOrder selectByUserIdAndSeckillId(@Param("userId") Long userId, @Param("seckillId") Long seckillId);

    @Insert("insert into t_seckill_order(user_id, seckill_id, order_no) values(#{userId}, #{seckillId}, #{orderNo})")
    void insert(SeckillOrder seckillOrder);
}

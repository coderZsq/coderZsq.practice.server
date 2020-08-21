package com.seemygo.shop.cloud.mapper;

import com.seemygo.shop.cloud.domain.SeckillGood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SeckillGoodMapper {

    @Select("select * from t_seckill_goods")
    List<SeckillGood> selectList();

    @Select("select * from t_seckill_goods where id = #{seckillId}")
    SeckillGood selectByPrimaryKey(Long seckillId);

    @Update("update t_seckill_goods set stock_count = stock_count - 1 where id = #{seckillId} and stock_count > 0")
    int decrStockCount(Long seckillId);

    @Update("update t_seckill_goods set stock_count = stock_count + 1 where id = #{seckillId}")
    void incrStockCount(Long seckillId);
}

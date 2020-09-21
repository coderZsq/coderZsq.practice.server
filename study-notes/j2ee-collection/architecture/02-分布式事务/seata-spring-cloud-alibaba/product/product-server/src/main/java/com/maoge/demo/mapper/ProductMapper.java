package com.maoge.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper {

    @Update("update product set stock=stock-#{num} where id=#{id}")
    public void reduce(Integer id, Integer num);
}

package com.sq.demo.xatransdemo.mapper.nonghang;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NhAccountMapper {
    @Update("UPDATE t_account SET amount = amount - #{amount} WHERE id=#{id}")
    void tranOut(Integer id,Integer amount);
}



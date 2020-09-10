package com.sq.demo.xatransdemo.mapper.gonghang;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GhAccountMapper {
    @Update("UPDATE t_account SET amount = amount + #{amount} WHERE id=#{id}")
    void tranIn(Integer id,Integer amount);
}

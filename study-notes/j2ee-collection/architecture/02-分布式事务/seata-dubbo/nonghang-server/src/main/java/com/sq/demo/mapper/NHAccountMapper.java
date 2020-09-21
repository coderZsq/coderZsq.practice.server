package com.sq.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface NHAccountMapper {
    @Update("update t_account set amount=amount-#{amount} where id=#{id}")
    public void transOut(@Param("id") Long id, @Param("amount") Integer amount);
}

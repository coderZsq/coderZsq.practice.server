package com.maoge.demo.mapper;

import com.maoge.demo.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    @Update("update account set sum=sum-#{amount} where id=#{id}")
    public int reduce(Integer id,Integer amount);

    @Select("select * from account where id=#{id} for update ")
    Account queryById(Integer id);
}

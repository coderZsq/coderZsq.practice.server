package com.sq.demo.mysqldemo.mapper;

import com.sq.demo.mysqldemo.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name) values(#{name})")
    public int insert(User user);

    @Delete("delete from user where name=#{userName}")
    public int delete(String userName);
}

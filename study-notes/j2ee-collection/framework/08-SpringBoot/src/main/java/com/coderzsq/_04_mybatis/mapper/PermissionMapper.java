package com.coderzsq._04_mybatis.mapper;

import com.coderzsq._04_mybatis.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("select * from permission")
    public List<Permission> list();

    @Insert("insert into permission(name, expression) values(#{name}, #{expression})")
    public void save(Permission permission);
}

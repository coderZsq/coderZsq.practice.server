package com.sq.jk.mapper;

import com.sq.jk.pojo.po.DictType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DictTypeMapper {
    @Select("SELECT id, name, value, intro FROM dict_type")
    List<DictType> list();
}

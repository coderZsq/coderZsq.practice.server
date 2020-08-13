package com.sq.dao;

import com.sq.bean.IdCard;
import org.apache.ibatis.annotations.Select;

public interface IdCardDao {
    @Select("SELECT * FROM id_card WHERE person_id = #{personId}")
    IdCard getByPerson(Integer personId);
}

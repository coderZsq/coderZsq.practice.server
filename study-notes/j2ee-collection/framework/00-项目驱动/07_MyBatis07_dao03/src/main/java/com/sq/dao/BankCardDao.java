package com.sq.dao;

import com.sq.bean.BankCard;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BankCardDao {
    @Select("SELECT * FROM bank_card WHERE person_id = #{personId}")
    List<BankCard> listByPerson(Integer personId);
}

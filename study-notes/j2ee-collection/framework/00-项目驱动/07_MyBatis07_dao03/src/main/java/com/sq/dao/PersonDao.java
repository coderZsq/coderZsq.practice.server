package com.sq.dao;

import com.sq.bean.Person;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PersonDao {
    @Select("SELECT * FROM person WHERE id = #{id}")
    @Results(id = "get", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            // 身份证
            @Result(
                    property = "idCard",
                    column = "id",
                    one = @One(fetchType = FetchType.LAZY, select = "com.sq.dao.IdCardDao.getByPerson")
            ),
            // 银行卡
            @Result(
                    property = "bankCards",
                    column = "id",
                    many = @Many(fetchType = FetchType.LAZY, select = "com.sq.dao.BankCardDao.listByPerson")
            ),
            // 工作
            @Result(
                    property = "jobs",
                    column = "id",
                    many = @Many(fetchType = FetchType.LAZY, select = "com.sq.dao.JobDao.listByPerson")
            ),
    })
    Person get(Integer id);

    @Select("SELECT * FROM person")
    // 引用id为get的@Results
    @ResultMap("get")
    List<Person> list();
}

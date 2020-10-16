package com.sq.dao;

import com.sq.domain.Skill;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SkillDao {
    @Insert("INSERT INTO skill(name, level) VALUES (#{name}, #{level})")
    boolean save(Skill skill);

    @Update("UPDATE skill SET name = #{name}, level = #{level} WHERE id = #{id}")
    boolean update(Skill skill);

    @Delete("DELETE FROM skill WHERE id = #{id}")
    boolean remove(Integer id);

    @Select("SELECT * FROM skill")
    List<Skill> list();

    @Select("SELECT * FROM skill WHERE id = #{id}")
    Skill get(Integer id);
}

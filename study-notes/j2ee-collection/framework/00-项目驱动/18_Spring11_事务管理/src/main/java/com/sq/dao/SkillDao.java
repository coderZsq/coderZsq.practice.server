package com.sq.dao;

import com.sq.domain.Skill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SkillDao {
    List<Skill> list();

    @Insert("INSERT INTO skill(name, level) VALUES (#{name}, #{level})")
    boolean save(Skill skill);

    @Update("UPDATE skill SET name = #{name}, level = #{level} WHERE id = #{id}")
    boolean update(Skill skill);
}

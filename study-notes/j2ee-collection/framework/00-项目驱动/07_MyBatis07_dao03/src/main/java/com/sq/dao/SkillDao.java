package com.sq.dao;

import com.sq.bean.Skill;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.LruCache;

import java.util.List;

// @CacheNamespace(flushInterval = 600000, size = 512, readWrite = true)
public interface SkillDao {
    // @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)

    // @Options(useGeneratedKeys = true, keyProperty = "id")

    @Insert("INSERT INTO skill(name, level) VALUES (#{name}, #{level})")
    boolean save(Skill skill);

    @Update("UPDATE skill SET name = #{name}, level = #{level} WHERE id = #{id}")
    boolean update(Skill skill);

    @Delete("DELETE FROM skill WHERE id = #{id}")
    boolean remove(Integer id);

    @Select("SELECT * FROM skill WHERE id = #{id}")
    Skill get(Integer id);

    @Select("SELECT * FROM skill")
    List<Skill> list();

    @Select("SELECT * FROM skill LIMIT #{start}, #{size}")
    List<Skill> listByStartAndSize(@Param("start") int start, @Param("size") int size);
}

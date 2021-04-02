package com.sq.imaginist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.imaginist.pojo.po.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface ArticleMapper extends BaseMapper<Article> {
    @Override
    @Insert("INSERT INTO article " +
            "(id, title, type, words, duration, date, content, preview)" +
            "VALUES" +
            "(#{id}, #{title}, #{type}, #{words}, #{duration}, #{date}, #{content}, #{preview})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Article article);
}
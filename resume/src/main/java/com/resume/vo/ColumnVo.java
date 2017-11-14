package com.resume.vo;

import java.util.List;

/**
 * Created by zhushuangquan on 14/11/2017.
 */
public class ColumnVo {

    private String name;

    private List<ArticleVo> articles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticleVo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleVo> articles) {
        this.articles = articles;
    }
}

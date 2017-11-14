package com.resume.pojo;

public class Article {
    private Integer id;

    private Integer columnId;

    private String name;

    private String href;

    public Article(Integer id, Integer columnId, String name, String href) {
        this.id = id;
        this.columnId = columnId;
        this.name = name;
        this.href = href;
    }

    public Article() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }
}
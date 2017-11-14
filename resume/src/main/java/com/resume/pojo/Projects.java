package com.resume.pojo;

public class Projects {
    private Integer id;

    private String src;

    private String href;

    private String name;

    private String description;

    private String text1;

    private String text2;

    private String text3;

    public Projects(Integer id, String src, String href, String name, String description, String text1, String text2, String text3) {
        this.id = id;
        this.src = src;
        this.href = href;
        this.name = name;
        this.description = description;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    public Projects() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1 == null ? null : text1.trim();
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2 == null ? null : text2.trim();
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3 == null ? null : text3.trim();
    }
}
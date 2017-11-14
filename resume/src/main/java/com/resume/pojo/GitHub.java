package com.resume.pojo;

public class GitHub {
    private Integer id;

    private String name;

    private String description;

    private String href;

    private String color;

    private String language;

    private String star;

    public GitHub(Integer id, String name, String description, String href, String color, String language, String star) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.href = href;
        this.color = color;
        this.language = language;
        this.star = star;
    }

    public GitHub() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star == null ? null : star.trim();
    }
}
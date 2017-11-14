package com.resume.pojo;

public class Column {
    private Integer id;

    private String name;

    public Column(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Column() {
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
}
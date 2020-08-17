package com.sq.bean;

import org.apache.ibatis.annotations.Param;

public class Skill extends Bean {
    private String name;
    private Integer level;

    public Skill() {
    }

    public Skill(@Param("name") String name, @Param("level") Integer level) {
        this.name = name;
        this.level = level;
    }

    public Skill(Integer level, String name) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id='" + getId() + '\'' +
                "createdTime='" + getCreatedTime() + '\'' +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}

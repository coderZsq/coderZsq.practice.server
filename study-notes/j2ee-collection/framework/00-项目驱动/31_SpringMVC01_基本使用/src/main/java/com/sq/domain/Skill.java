package com.sq.domain;

public class Skill {
    private String name;
    private String intro;
    private Integer level;

    public void setName(String name) {
        this.name = name;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", level=" + level +
                '}';
    }
}

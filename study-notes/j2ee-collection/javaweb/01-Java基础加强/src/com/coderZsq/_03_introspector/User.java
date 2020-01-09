package com.coderZsq._03_introspector;

public class User extends Object {
    private String name;
    private int age;
    private boolean man;

    // 属性名: name
    public void setName(String name) {
        this.name = name;
    }

    // 属性名: age
    public void setAge(int age) {
        this.age = age;
    }

    // 属性名: man
    public boolean isMan() {
        return man;
    }

    // 属性名: man
    public void setMan(boolean man) {
        this.man = man;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", man=" + man +
                '}';
    }
}

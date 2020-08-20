package com.sq.domain;

import java.util.Date;

public class Person {
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "birthday=" + birthday +
                '}';
    }
}

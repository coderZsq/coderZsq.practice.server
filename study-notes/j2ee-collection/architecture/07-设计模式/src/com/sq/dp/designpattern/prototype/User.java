package com.sq.dp.designpattern.prototype;

public class User implements Cloneable {
    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    protected Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public String toString() {
        return "User@" + super.hashCode() + "{" +
                "username='" + username + '\'' +
                '}';
    }
}

package com.sq.domain;

public class Rocket {
    private static final Rocket INSTANCE = new Rocket();
    private Rocket() {}
    public static Rocket getInstance() {
        return INSTANCE;
    }
}

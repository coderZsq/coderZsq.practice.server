package com.coderZsq;

public class WrongAgeException extends RuntimeException {
    private int age;
    public WrongAgeException(int age) {
        super("wrong age:" + age + ", age must be > 0");
    }
}

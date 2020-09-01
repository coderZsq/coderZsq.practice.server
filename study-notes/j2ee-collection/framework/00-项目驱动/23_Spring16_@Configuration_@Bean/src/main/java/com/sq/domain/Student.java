package com.sq.domain;

public class Student {
    private Dog dog;

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Student{" +
                "dog=" + dog +
                '}';
    }
}

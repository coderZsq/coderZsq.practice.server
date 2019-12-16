package com.coderZsq;

public class Student extends Person {
    static {
        System.out.println("Student - static block");
    }

    {
        System.out.println("Student - block");
    }

    public Student() {
        System.out.println("Student - constructor");
    }

    public static void main(String[] args) {
        Student stu = new Student();
        /*
        Person - static block
        Student - static block
        Person - block
        Person - constructor
        Student - block
        Student - constructor
        */
    }
}

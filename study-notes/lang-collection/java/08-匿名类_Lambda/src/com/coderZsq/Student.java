package com.coderZsq;

public class Student extends Person {
    public void setAge(int age) {
        System.out.println("Student - setAge - " + age);
    }
    public void show() {
        Testable t1 = v -> super.setAge(v);
        // Person - setAge - 10
        t1.test(10);

        Testable t2 = super::setAge;
        // Person - setAge - 10
        t2.test(10);
    }
}

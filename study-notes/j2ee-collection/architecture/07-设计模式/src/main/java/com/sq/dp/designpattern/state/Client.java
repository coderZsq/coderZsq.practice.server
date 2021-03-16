package com.sq.dp.designpattern.state;

public class Client {
    public static void main(String[] args) {
        System.out.println("学生成绩测试: ");
        Student student = new Student();
        student.add(40);
        student.add(30);
        student.add(25);
        student.add(-15);
        student.add(-30);
    }
}

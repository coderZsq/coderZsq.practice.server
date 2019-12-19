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

        /*
         * final
         *
         * 被 final 修饰的类:不能被继承
         * 被 final 修饰的方法:不能被重写
         * 被 final 修饰的变量:只能进行1次赋值
         * */
    }

    /*
     * 常量(Constant)
     *
     * 常量的写法
     *
     * 如果将基本类型或字符串定义为常量，并且在编译时就能确定值
     * 编译器会使用常量值替代各处的常量名(类似于 C 语言的宏替换)
     * 称为编译时常量( compile-time constant)
     * */
    public static final double PI = 3.14159265358979323846;

    public static final int NOT_FOUND = -1;
    private static final int DEFAULT_SIZE = 10;
}

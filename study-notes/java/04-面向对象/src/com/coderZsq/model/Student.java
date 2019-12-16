package com.coderZsq.model;

/*
 * 继承(Inheritance)
 * */

/*
 * Object
 * 任何类最终都继承自 java.lang.Object，一般称它为基类
 * */
public class Student extends Person {
    public int no;
    public int age = 1;

    public void study() {
        System.out.println(age + "_" + no + "_study");
    }

    public void show() {
        System.out.println(age);
        System.out.println(this.age);
        System.out.println(super.age);
    }

    public Student() {
    }

    /*
     * 构造方法的细节
     *
     * 子类的构造方法必须先调用父类的构造方法，再执行后面的代码
     * 如果子类的构造方法没有显式调用父类的构造方法
     * 编译器会自动调用父类无参的构造方法(若此时父类没有无参的构造方法，编译器将报错)
     * */
    public Student(int no) {
        /*
         * super
         *
         * super 的常见用途是
         * 访问父类中定义的成员变量
         * 调用父类中定义的方法(包括构造方法)
         * */
        super(0);
        this.no = no;
    }


    public static void main(String[] args) {
        {
            Student stu = new Student();
            stu.age = 20;
            stu.no = 1;
            stu.walk();
            stu.study();

            Person person = new Person();
            person.age = 15;
            person.walk();
        }
        /*
         * 同名的成员变量
         *
         * 子类可以定义跟父类同名的成员变量(但不推荐这么做)
         * 打印结果是:20、20、2
         * */
        {
            Student stu = new Student();
            stu.age = 20;
            stu.show();
        }
    }
}

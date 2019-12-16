package com.coderZsq.model;

public class Person {
    /*
     * 访问控制(Access Control)
     *
     * Java 中有 4 个级别的访问权限，从高到低如下所示
     * public:在任何地方都是可见的 protected:仅在自己的包中、自己的子类中可见
     * 无修饰符(package-private):仅在自己的包中可见
     * private:仅在自己的类中可见
     *
     * 使用注意
     * 上述 4 个访问权限都可以修饰类的成员，比如成员变量、方法、嵌套类(Nested Class)等
     * 只有 public、无修饰符(package-private)可以修饰顶级类(Top-level Class)
     * 上述 4 个访问权限不可以修饰局部类(Local Class)、局部变量
     * 一个 Java 源文件中可以定义多个顶级类，public 顶级类的名字必须和文件名一样
     * */
    public int age = 2;
    public Dog dog;

    public void walk() {
        System.out.println(age + "_walk");
    }

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    /*
     * 封装
     *
     * 成员变量 private 化，提供 public 的getter、setter
     * */
    private int age2;
    private String name;

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * toString方法
     *
     * 当打印一个对象时，会自动调用对象的 toString 方法，并将返回的字符串打印出来
     * toString 方法来源于基类 java.lang.Object，默认实现如下所示
     * Eclipse 中有一个可以自动生成 getter、setter、constructor、toString 等常用代码的快捷键
     * Shift + Alt + S
     * */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}

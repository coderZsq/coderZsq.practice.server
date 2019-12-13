package com.coderZsq.model;

public class Cat {
    public String name;
    public int age;
    public int price;

    /*
     * this
     *
     * this是一个指向当前对象的引用，常见用途是
     * 访问当前类中定义的成员变量
     * 调用当前类中定义的方法(包括构造方法)
     *
     * this 的本质是一个隐藏的、位置最靠前的方法参数
     *
     * 只能在构造方法中使用this 调用其他构造方法
     *
     * 如果在构造方法中调用了其他构造方法
     * 构造方法调用语句必须是构造方法中的第一条语句
     * */
    public Cat(String name, int age, int price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public Cat(String name) {
        this(name, 0, 0);
    }
}

/*
 * 包(package)
 *
 * Java 中的包就是其他编程语言中的命名空间，包的本质是文件夹，常见作用是
 * 将不同的类进行组织管理、访问控制
 * 解决命名冲突
 *
 * 命名建议
 * 为保证包名的唯一性，一般包名都是以公司域名的倒写开头，比如 com.baidu.*
 * 全小写(以避免与某些类名或者接口名冲突)
 *
 * 类的第一句代码必须使用 package 声明自己属于哪个包
 * 比如 package com.coderZsq.model;
 * */

/*
 * 包名的细节
 *
 * 如果公司域名有非法字符，建议添加下划线(_)来使包名合法化
 * */
package com.coderZsq.model;

/*
 * 类的定义、对象的创建
 * 成员变量(Member Variable)也叫做字段(Field)
 * */
public class Dog {
    // 成员变量
    public int age;
    public double weight;
    public int price;

    /*
     * 构造方法(Constructor)
     *
     * 构造方法，也叫构造器，能够更方便地创建一个对象
     * 方法名必须和类名一样
     * 没有返回值类型
     * 可以重载
     * */
    public Dog() {

    }

    /*
     * 默认构造方法(Default Constructor)
     *
     * 如果一个类没有自定义构造方法，编译器会自动为它提供无参数的默认构造方法
     * 一旦自定义了构造方法，默认构造方法就不再存在
     * */
    public Dog(int age) {
        this.age = age;
    }

    public Dog(int age, double weight) {
        this.age = age;
        this.weight = weight;
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog();
        Dog dog2 = new Dog(18);
        Dog dog3 = new Dog(20, 6.66);
    }

    // 方法
    public void run() {
        System.out.println(age + "_" + weight + "_walk");
    }

    public void eat(String food) {
        System.out.println(age + "_" + weight + "_eat_" + food);
    }

    public void eat() {
        System.out.println(price + "_eat");
    }
}
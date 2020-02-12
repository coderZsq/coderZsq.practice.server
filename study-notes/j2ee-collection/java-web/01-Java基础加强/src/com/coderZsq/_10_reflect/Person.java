package com.coderZsq._10_reflect;

public class Person {
    static {
        System.out.println("这是静态代码块");
    }

    // 成员变量
    private Long id;
    public String name;
    public int age;

    // 构造方法
    public Person() {
        // System.out.println("空参数public构造器被执行");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("带有String的public构造器被执行");
    }

    // 私有的构造方法
    private Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("带有String, int的private构造器被执行");
    }

    public Person(Long id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println("带有Long, String, int的public构造器被执行");
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // 成员方法
    // 无参无返回的方法
    public void method1() {
        System.out.println("无参无返回的public方法被执行");
    }

    // 有参无返回的方法
    public void method2(String name) {
        System.out.println("有参无返回的public方法被执行 name= " + name);
    }

    // 无参有返回的方法
    public int method3() {
        System.out.println("无参有返回的public方法被执行");
        return 123;
    }

    // 有参有返回方法
    public String method4(String name) {
        System.out.println("有参有返回的public方法被执行");
        return "coderZsq - " + name;
    }

    // 私有方法
    private void method5() {
        System.out.println("private私有方法被执行");
    }

    // 静态方法
    public static void method6(String name) {
        System.out.println("静态方法被执行 name= " + name);
    }
}

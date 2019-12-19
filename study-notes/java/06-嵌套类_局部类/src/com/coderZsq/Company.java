package com.coderZsq;


public class Company {
    private String name;
    public Company(String name) {
        this.name = name;
    }
    public void fire(Employee e) {
        System.out.println(name + " fire " + e.no);
    }
    /*
     * 内部类(Inner Class)
     *
     * 内部类:没有被static 修饰的嵌套类，非静态嵌套类
     *
     * 跟实例变量、实例方法一样，内部类与外部类的实例相关联
     * 必须先创建外部类实例，然后再用外部类实例创建内部类实例
     * 内部类不能定义除编译时常量以外的任何 static 成员
     *
     * 内部类可以直接访问外部类中的所有成员(即使被声明为 private)
     *
     * 外部类可以直接访问内部类实例的成员变量、方法(即使被声明为 private)
     * */
    public class Employee {
        private int no;
        public Employee(int no) {
            this.no = no;
        }
        public void show() {
            System.out.println(name + " : " + no);
        }
    }

    public static void main(String[] args) {
        Company cpy = new Company("Google");
        Employee emp = cpy.new Employee(1);
        emp.show(); // Google : 1
        cpy.fire(emp); // Google fire 1
    }
}

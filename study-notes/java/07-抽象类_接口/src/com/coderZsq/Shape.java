package com.coderZsq;

/*
 * 抽象类(Abstract Class)
 *
 * 抽象类:被 abstract 修饰的类
 * 可以定义抽象方法
 * 不能实例化，但可以自定义构造方法
 * 子类必须实现抽象父类中的所有抽象方法(除非子类也是一个抽象类)
 * 可以像非抽象类一样定义成员变量、常量、嵌套类型、初始化块、非抽象方法等
 * 也就说，抽象类也可以完全不定义抽象方法
 *
 * 常见使用场景
 * 抽取子类的公共实现到抽象父类中，要求子类必须要单独实现的定义成抽象方法
 * */
public abstract class Shape {
    protected double area;
    protected double girth;

    public double getArea() {
        return area;
    }

    public double getGirth() {
        return girth;
    }

    public void show() {
        calculate();
        System.out.println("area is " + area);
        System.out.println("girth is " + girth);
    }

    /*
     * 抽象方法(Abstract Method)
     *
     * 抽象方法:被abstract 修饰的方法
     * 只有方法声明，没有方法实现(参数列表后面没有大括号，而是分号)
     * 不能是 private 权限(因为定义抽象方法的目的让子类去实现)
     * 只能是实例方法，不能是类方法
     * 只能定义在抽象类、接口中
     * */
    protected abstract void calculate();
}

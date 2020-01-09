package com.coderZsq;

/*
 * 基本类型的缺陷
 *
 * 对比引用类型，基本类型存在的一些缺陷
 * 无法表示不存在的值(null 值)
 * 不能利用面向对象的方式去操作基本类型(比如直接用基本类型调用方法)
 * 当方法参数是引用类型时，基本类型无法传递
 *
 * 解决方案:可以自己将基本类型包装成引用类型
 * */
public class IntObject {
    public int value;

    public IntObject(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        IntObject[] data = {
                new IntObject(-100), new IntObject(100),
                null, new IntObject(0)
        };
        for (IntObject intObject : data) {
            if (intObject == null) {
                System.out.println("没有值");
            } else {
                System.out.println(intObject.value);
            }
        }
    }
}

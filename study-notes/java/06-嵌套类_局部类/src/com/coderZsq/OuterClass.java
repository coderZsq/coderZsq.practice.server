package com.coderZsq;

/*
 * 嵌套类(Nested Class)
 *
 * 嵌套类:定义在另一个类中的类
 * 在嵌套类外层的类，称为:外部类(Outer Class)
 * 最外层的外部类，称为:顶级类(Top-level Class)
 * */
public class OuterClass {
    private int x = 1;

    /*
     * 静态嵌套类(Static Nested Class)
     *
     * 静态嵌套类:被static 修饰的嵌套类
     * 静态嵌套类在行为上就是一个顶级类，只是定义的代码写在了另一个类中
     * 对比一般的顶级类，静态嵌套类多了一些特殊权限
     * 可以直接访问外部类中的成员(即使被声明为 private)
     * */

    /*
     * 什么情况使用嵌套类?
     *
     * 如果类 A 只用在类 C 内部，可以考虑将类 A 嵌套到类 C 中
     * 封装性更好
     * 程序包更加简化
     * 增强可读性、维护性
     *
     * 如果类 A 需要经常访问类 C 的非公共成员，可以考虑将类 A嵌套到类 C 中
     * 另外也可以根据需要将类 A 隐藏起来，不对外暴露
     *
     * 如果需要经常访问非公共的实例成员，设计成内部类(非静态嵌套类)，否则设计成静态嵌套类
     * 如果必须先有 C 实例，才能创建 A 实例，那么可以将 A 设计为 C 的内部类
     * */

    // 静态嵌套类
    static class StaticNestedClass {

    }

    // 非静态嵌套类 (内部类)
    public class InnerClass {
        private int x = 2;

        public void show() {
            System.out.println(x); // 2
            System.out.println(this.x); // 2
            System.out.println(OuterClass.this.x); // 1
        }
    }

    public static void main(String[] args) {
        new OuterClass().new InnerClass().show();
    }
}

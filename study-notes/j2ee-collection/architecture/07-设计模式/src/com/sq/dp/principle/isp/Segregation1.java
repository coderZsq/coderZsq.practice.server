package com.sq.dp.principle.isp;

interface Interface1 {
    // 类型一
    void operation1();
}

interface Interface2 {
    // 类型二
    void operation2();

    void operation3();
}

interface Interface3 {
    // 类型三
    void operation4();

    void operation5();
}

/**
 * 接口隔离原则
 * 1. 客户端不应该依赖它不需要的接口
 * 2. 类间的依赖关系应该建立在最小的接口上
 */
public class Segregation1 {
    public static void main(String[] args) {
        // 提供服务: 1, 2, 3
        B b = new B();
        // 提供服务: 1, 4, 5
        D d = new D();

        // 消费服务: B ---> [1, 2, 3]
        A a = new A();
        a.used1(b);
        a.used2(b);
        a.used3(b);

        // 消费服务: D ---> [1, 4, 5]
        C c = new C();
        c.used1(d);
        c.used4(d);
        c.used5(d);

        // 1. 消费方不应该依赖它不需要的接口
        // 2. 类间的依赖关系应该建立在最小的接口上
    }
}

class A {
    void used1(Interface1 interface1) {
        // B -> operation1
        interface1.operation1();
    }

    void used2(Interface2 interface2) {
        // B -> operation2
        interface2.operation2();
    }

    void used3(Interface2 interface2) {
        // B -> operation3
        interface2.operation3();
    }
}

class C {
    void used1(Interface1 interface1) {
        // D -> operation1
        interface1.operation1();
    }

    void used4(Interface3 interface3) {
        // D -> operation4
        interface3.operation4();
    }

    void used5(Interface3 interface3) {
        // D -> operation5
        interface3.operation5();
    }
}

class B implements Interface1, Interface2 {
    @Override
    public void operation1() {
        System.out.println("B -> operation1()");
    }

    @Override
    public void operation2() {
        System.out.println("B -> operation2()");
    }

    @Override
    public void operation3() {
        System.out.println("B -> operation3()");
    }
}

class D implements Interface1, Interface3 {
    @Override
    public void operation1() {
        System.out.println("D -> operation1()");
    }

    @Override
    public void operation4() {
        System.out.println("D -> operation4()");
    }

    @Override
    public void operation5() {
        System.out.println("D -> operation5()");
    }
}
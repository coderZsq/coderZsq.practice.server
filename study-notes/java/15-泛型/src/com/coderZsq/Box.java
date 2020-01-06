package com.coderZsq;

public class Box<E> {
    private E element;

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Box() {}

    public Box(E element) {
        this.element = element;
    }

    // 不能创建类型参数的实例
    public void add(Class<E> cls) throws Exception {
        // error
        // E e1 = new E();

        // ok
        E e2 = cls.newInstance();
    }

    // 不能用类型参数定义静态变量
    // error
    // private static E value;

    // 泛型类型的类型参数不能用在静态方法上
    // error
    // public static void show(E value) {}
}

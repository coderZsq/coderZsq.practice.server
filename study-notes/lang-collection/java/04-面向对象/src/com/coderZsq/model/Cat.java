package com.coderZsq.model;

public class Cat extends Animal {
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
    public Cat() {
    }

    public Cat(String name, int age, int price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public Cat(String name) {
        this(name, 0, 0);
    }

    /*
     * 方法的重写(Override)
     *
     * 重写:子类的方法签名与父类一样。也叫做覆盖、覆写
     * */
    @Override
    public void speak() {
        super.speak();
        run();
        this.run();
        super.run();

        System.out.println("Cat - speak");
    }

    /*
     * 重写的注意点
     *
     * 子类 override 的方法权限必须 ≥ 父类的方法权限
     * 子类 override 的方法返回值类型必须 ≤ 父类的方法返回值类型
     * 子类 ≤ 父类
     * */

    /*
    * 注解(Annotation)
    *
    * 2个常见的注解
    * @Override: 告诉编译器这是一个重写后的方法
    *
    * @SuppressWarnings("警告类别"): 让编译器不生成警告信息
    * @SuppressWarnings({"rawtypes", "unused"})
    * @SuppressWarnings("unused")
    * */
    @Override
    public void run() {
        System.out.println("Cat - run");
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.speak();
    }
}

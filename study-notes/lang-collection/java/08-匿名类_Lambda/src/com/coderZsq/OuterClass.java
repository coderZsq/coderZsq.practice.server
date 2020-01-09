package com.coderZsq;

/*
 * Lambda 的使用注意
 *
 * Lambda 只能访问 final 或者 有效 final 的局部变量
 * Lambda 没有引入新的作用域
 * */
public class OuterClass {
    private int age = 1;

    public class InnerClass {
        private int age = 2;

        void inner() {
            // int v= 4; // error
            Testable t = v -> {
                System.out.println(v); // 3
                System.out.println(age); // 2
                System.out.println(this.age); //2
                System.out.println(InnerClass.this.age); // 2
                System.out.println(OuterClass.this.age); // 1
            };
            t.test(3);
        }
    }
}

package com.coderZsq;

/*
 * 匿名类 vs Lambda
 * */
public class OuterClass2 {
    private int age = 1;

    public class InnerClass {
        private int age = 2;

        void inner() {
            int v = 4;
            Testable t = new Testable() {
                @Override
                public void test(int v) {
                    System.out.println(v); // 3
                    System.out.println(age); // 2
                    // System.out.println(this.age); // error
                    System.out.println(InnerClass.this.age); // 1
                    System.out.println(OuterClass2.this.age); // 1
                }
            };
            t.test(3);
        }
    }
}

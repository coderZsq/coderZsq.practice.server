package com.coderZsq;

public class Person {
    public void eat(Eatable e) {
        System.out.println(
                "eat - " + e.name()
                        + " - " + e.energy()
        );
    }

    public void setAge(int age) {
        System.out.println(
                "Person - setAge - " + age
        );
    }

    static void execute(Testable t, int v) {
        t.test(v);
    }

    public Person() {
    }

    public Person(int age) {
        System.out.println("Person - " + age);
    }

    public static void main(String[] args) {
        /*
         * 引用特定对象的实例方法
         * */
        {
            // 10
            execute(v -> System.out.println(v), 10);
            // Person - setAge - 10
            execute(v -> new Person().setAge(v), 10);

            // 20
            execute(System.out::println, 20);
            // Person - setAge - 20
            execute(new Person()::setAge, 20);
        }

        /*
         * 引用构造方法
         * */
        {
            Testable3 t1 =  v -> new Person(v);
            // Person - 18
            // com.coderZsq.Person@7ef20235
            System.out.println(t1.test(18));

            Testable3 t2 = Person::new;
            // Person - 18
            // com.coderZsq.Person@15aeb7ab
            System.out.println(t2.test(18));
        }
    }

    public void show() {
        Testable t1 = v -> setAge(v);
        // setAge - 10
        t1.test(10);

        Testable t2 = this::setAge;
        // setAge - 10
        t2.test(10);
    }
}

package com.coderZsq;

public interface Dog5 extends Sleepable, Eatable2 {
    static void eat(String name) {
        System.out.println("Dog - eat - " + name);
    }

    static void main(String[] args) {
        Dog5.eat("1"); // Dog - eat - 1
        Eatable2.eat("2"); // Eatable - eat 2
        Sleepable.eat("3"); // Sleepable - eat - 3
    }
}

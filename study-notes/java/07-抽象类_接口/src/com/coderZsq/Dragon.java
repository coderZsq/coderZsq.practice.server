package com.coderZsq;

public class Dragon implements Fly, Fire {
    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        System.out.println(dragon.myself());
        // I am able to fly.
    }
}

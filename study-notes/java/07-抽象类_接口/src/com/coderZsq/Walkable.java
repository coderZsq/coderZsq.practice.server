package com.coderZsq;

public interface Walkable {
    default void run() {
        System.out.println("Walkable - run");
    }
}

package com.coderZsq;

public interface Fly extends Animal3 {
    default String myself() {
        return "I am able to fly.";
    }
}

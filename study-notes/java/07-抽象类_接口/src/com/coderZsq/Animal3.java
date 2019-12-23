package com.coderZsq;

public interface Animal3 {
    default String myself() {
        return "I am an animal.";
    }
}

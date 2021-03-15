package com.sq.dp.designpattern.builder.example1;

public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("办公用")
                .cpu("i9")
                .gpu("CGX2090")
                .memory("32G")
                .build();
        computer.display();
    }
}

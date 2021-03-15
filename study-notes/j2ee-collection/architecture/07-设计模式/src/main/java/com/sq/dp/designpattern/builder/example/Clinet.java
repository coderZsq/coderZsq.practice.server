package com.sq.dp.designpattern.builder.example;

public class Clinet {
    public static void main(String[] args) {
        AbstructComputerBuilder builder = new ComputerBuilder("办公使用");
        ComputerDirector director = new ComputerDirector(builder);
        Computer computer = director.construct(null, null, null);
        computer.display();

        AbstructComputerBuilder builder1 = new ComputerBuilder("游戏本");
        ComputerDirector director1 = new ComputerDirector(builder1);
        Computer computer1 = director1.construct("i9", "GTX3000", "32G");
        computer1.display();
    }
}

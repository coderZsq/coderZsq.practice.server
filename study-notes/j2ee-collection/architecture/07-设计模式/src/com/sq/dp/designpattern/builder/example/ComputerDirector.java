package com.sq.dp.designpattern.builder.example;

public class ComputerDirector {
    private AbstructComputerBuilder builder;

    public ComputerDirector(AbstructComputerBuilder builder) {
        this.builder = builder;
    }

    public Computer construct(String cpu, String gpu, String memory) {
        builder.buildCPU(cpu);
        builder.buildGPU(gpu);
        builder.buildMemory(memory);
        return builder.getComputer();
    }
}

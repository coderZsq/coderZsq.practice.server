package com.sq.dp.designpattern.builder.example;

abstract public class AbstructComputerBuilder {
    protected Computer computer = null;

    abstract public void buildCPU(String cpu);

    abstract public void buildGPU(String gpu);

    abstract public void buildMemory(String memory);

    public Computer getComputer() {
        return this.computer;
    }
}

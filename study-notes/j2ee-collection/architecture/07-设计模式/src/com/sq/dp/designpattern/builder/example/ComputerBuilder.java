package com.sq.dp.designpattern.builder.example;

public class ComputerBuilder extends AbstructComputerBuilder {
    public ComputerBuilder(String purpose) {
        computer = new Computer();
        // TODO
        computer.setPurpose(purpose);
    }

    @Override
    public void buildCPU(String cpu) {
        if (cpu == null || "".equals(cpu.trim())) {
            return;
        }
        computer.setCpu(cpu);
    }

    @Override
    public void buildGPU(String gpu) {
        if (gpu == null || "".equals(gpu.trim())) {
            return;
        }
        computer.setGpu(gpu);
    }

    @Override
    public void buildMemory(String memory) {
        if (memory == null || "".equals(memory.trim())) {
            return;
        }
        computer.setMemory(memory);
    }
}

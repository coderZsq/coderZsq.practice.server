package com.sq.dp.designpattern.builder.example;

public class Computer {
    private String purpose;
    private String cpu = "默认cpu";
    private String gpu = "默认gpu";
    private String memory = "默认内存";

    public void display() {
        System.out.println("电脑用途: " + this.purpose + ", 配置: cpu=" + this.cpu + ", gpu=" + this.gpu + ", memory=" + this.memory);
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }
}

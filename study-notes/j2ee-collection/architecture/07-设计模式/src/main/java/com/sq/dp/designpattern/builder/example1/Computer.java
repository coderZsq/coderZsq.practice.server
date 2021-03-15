package com.sq.dp.designpattern.builder.example1;

public class Computer {
    private String purpose;
    private String cpu;
    private String gpu;
    private String memory;

    private Computer(Builder builder) {
        this.purpose = builder.purpose;
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.memory = builder.memory;
    }

    public void display() {
        System.out.println("电脑用途: " + this.purpose + ", 配置: cpu=" + this.cpu + ", gpu=" + this.gpu + ", memory=" + this.memory);
    }

    public static class Builder {
        private String purpose;
        private String cpu = "默认cpu";
        private String gpu = "默认gpu";
        private String memory = "默认内存";

        public Builder(String purpose) {
            this.purpose = purpose;
        }

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder memory(String memory) {
            this.memory = memory;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

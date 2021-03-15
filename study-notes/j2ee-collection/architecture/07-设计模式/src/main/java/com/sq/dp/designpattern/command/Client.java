package com.sq.dp.designpattern.command;

public class Client {
    public static void main(String[] args) {
        // 接收者
        Processor processor = new Processor();
        // 命令
        Command add = new AddCommand(processor);
        Command subtract = new SubtractCommand(processor);

        // 发送者
        Calculator calc = new Calculator();
        calc.enter(1, 3).setCommand(add).call();
        calc.enter(5, 3).setCommand(subtract).call();
    }
}

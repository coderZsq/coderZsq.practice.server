package com.sq.dp.designpattern.command;

/**
 * 发送者: 计算器类
 */
public class Calculator {
    public int num1;
    public int num2;

    private Command command;

    public Calculator enter(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
        return this;
    }

    public Calculator setCommand(Command command) {
        command.setNum(this.num1, this.num2);
        this.command = command;
        return this;
    }

    public void call() {
        command.execute();
    }
}

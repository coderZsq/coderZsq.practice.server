package com.sq.dp.designpattern.command;

/**
 * 抽象命令类
 */
public interface Command {
    void execute();

    Command setNum(int num1, int num2);
}

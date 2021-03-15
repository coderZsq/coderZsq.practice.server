package com.sq.dp.designpattern.decorator;

public interface Logger {
    void trace(String msg);

    void info(String msg);

    void warn(String msg);

    void error(String msg);
}

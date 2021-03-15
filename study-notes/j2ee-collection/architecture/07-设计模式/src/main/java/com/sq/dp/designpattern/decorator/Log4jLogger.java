package com.sq.dp.designpattern.decorator;

public class Log4jLogger implements Logger {
    @Override
    public void trace(String msg) {
        System.out.println("[TRACE] 模拟 log4j 日志打印: " + msg);
    }

    @Override
    public void info(String msg) {
        System.out.println("[INFO] 模拟 log4j 日志打印: " + msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("[WARN] 模拟 log4j 日志打印: " + msg);
    }

    @Override
    public void error(String msg) {
        System.out.println("[ERROR] 模拟 log4j 日志打印: " + msg);
    }
}

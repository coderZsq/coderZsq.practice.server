package com.sq;

public class _00_MyProject {
    public static void main(String[] args) {
        _01_Log4j1.main(args);
        _02_Log4j2.main(args);

        _03_Logback.main(args);

        // _04_SLF4J_Log4j1.main(args);
        // _05_SLF4J_Log4j2.main(args);
    }
}

/*
    log4j-api: 门面接口 (log4j 2.x)
    slf4j-api: 门面接口

    logback-classic: 实现了slf4j-api门面接口的日志实现框架
    log4j-core: 实现了log4j-api门面接口的日志实现框架 (log4j 2.x)
    log4j: log4j 1.x的日志实现框架

    slf4j-log4j12: 实现了slf4j-api门面接口, 它的内部会调用log4j
    log4j-slf4j-impl: 实现了slf4j-api的门面接口, 它的内部会调用log4j-core

    log4j-over-slf4j: log4j的盗版实现, 它的内部会调用slf4j-api
    log4j-1.2-api: log4j的盗版实现, 它的内部会调用log4j-core
    log4j-to-slf4j: log4j-core的盗版实现, 它的内部会调用slf4j-api
*/

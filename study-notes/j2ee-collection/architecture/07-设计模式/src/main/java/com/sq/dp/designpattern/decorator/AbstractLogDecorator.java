package com.sq.dp.designpattern.decorator;

abstract public class AbstractLogDecorator implements Logger {
    private Logger logger;

    public AbstractLogDecorator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void trace(String msg) {
        logger.trace(msg);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }
}

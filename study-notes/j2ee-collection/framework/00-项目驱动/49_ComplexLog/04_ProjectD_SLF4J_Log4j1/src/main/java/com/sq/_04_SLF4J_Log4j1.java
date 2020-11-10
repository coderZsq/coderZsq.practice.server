package com.sq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _04_SLF4J_Log4j1 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(_04_SLF4J_Log4j1.class);
        logger.info("信息_INFO");
    }
}

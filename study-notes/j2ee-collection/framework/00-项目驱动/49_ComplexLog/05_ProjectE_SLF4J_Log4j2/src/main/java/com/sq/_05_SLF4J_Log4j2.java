package com.sq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _05_SLF4J_Log4j2 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(_05_SLF4J_Log4j2.class);
        logger.info("信息_INFO");
    }
}

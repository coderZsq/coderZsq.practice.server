package com.sq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class _02_Log4j2 {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(_02_Log4j2.class);
        logger.info("信息_INFO");
    }
}

package com.sq;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class _01_Log4j1 {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(_01_Log4j1.class);
        logger.info("信息_INFO");
    }
}

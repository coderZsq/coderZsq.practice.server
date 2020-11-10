package com.sq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _03_Logback {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(_03_Logback.class);
        logger.info("信息_INFO");
    }
}

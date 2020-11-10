package com.sq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestLog4j2 {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(TestLog4j2.class);
        for (int i = 0; i < 1000; i++) {
            logger.fatal("致命_FATAL");
            logger.error("错误_ERROR");
            logger.warn("警告_WARN");
            logger.info("信息_INFO");
            logger.debug("调试_DEBUG");
            logger.trace("痕迹_TRACE");
        }
    }
}

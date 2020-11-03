package com.sq.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestLog4j1_02 {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(TestLog4j1_02.class);
        logger.fatal("致命_FATAL");
        logger.error("错误_ERROR");
        logger.warn("警告_WARN");
        logger.info("信息_INFO");
        logger.debug("调试_DEBUG");
        logger.trace("痕迹_TRACE");
    }
}

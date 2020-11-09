package com.sq;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class TestJCL_Log4j1_02 {
    // private static final Log log = LogFactory.getLog(TestJCL_Log4j1_02.class);

    public static void main(String[] args) {
        log.fatal("致命_FATAL");
        log.error("错误_ERROR");
        log.warn("警告_WARN");
        log.info("信息_INFO");
        log.debug("调试_DEBUG");
        log.trace("痕迹_TRACE");
    }
}

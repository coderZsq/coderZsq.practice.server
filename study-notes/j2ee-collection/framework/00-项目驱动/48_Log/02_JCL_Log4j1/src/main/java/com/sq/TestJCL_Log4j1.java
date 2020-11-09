package com.sq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestJCL_Log4j1 {
    public static void main(String[] args) {
        // LogLog.setInternalDebugging(true);

        Log log = LogFactory.getLog(TestJCL_Log4j1.class);
        log.fatal("致命_FATAL");
        log.error("错误_ERROR");
        log.warn("警告_WARN");
        log.info("信息_INFO");
        log.debug("调试_DEBUG");
        log.trace("痕迹_TRACE");
    }
}

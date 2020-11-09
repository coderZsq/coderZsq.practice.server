package com.sq;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.helpers.LogLog;

@Slf4j
public class TestSLF4J_Log4j1 {
    public static void main(String[] args) {
        // Logger logger = LoggerFactory.getLogger(TestSLF4J_Log4j1.class);
        log.error("错误_ERROR");
        log.warn("警告_WARN");
        log.info("信息_INFO");
        log.debug("调试_DEBUG");
        log.trace("痕迹_TRACE");
    }
}

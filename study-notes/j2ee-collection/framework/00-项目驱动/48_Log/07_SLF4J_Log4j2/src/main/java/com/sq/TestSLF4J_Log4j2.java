package com.sq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSLF4J_Log4j2 {
    public static void main(String[] args) {
        log.error("错误_ERROR");
        log.warn("警告_WARN");
        log.info("信息_INFO");
        log.debug("调试_DEBUG");
        log.trace("痕迹_TRACE");
    }
}

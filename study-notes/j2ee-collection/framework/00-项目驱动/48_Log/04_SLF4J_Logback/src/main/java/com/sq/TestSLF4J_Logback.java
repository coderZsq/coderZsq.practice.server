package com.sq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSLF4J_Logback {
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            log.error("错误_ERROR");
            log.warn("警告_WARN");
            log.info("信息_INFO");
            log.debug("调试_DEBUG");
            log.trace("痕迹_TRACE");
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("666666666666666--------------------");
        }

        // 15:31:07.976 [main] ERROR com.sq.TestSLF4J_Logback - 错误_ERROR
        // %d{HH:mm:ss.SSS} [%t] %p %c - %m%n
    }
}

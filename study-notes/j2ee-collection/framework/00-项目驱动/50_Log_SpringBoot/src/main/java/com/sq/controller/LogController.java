package com.sq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogController {
    @GetMapping("/log")
    public String log() {
        log.info("info log-----");
        log.warn("warn log-----");
        return "log";
    }
}

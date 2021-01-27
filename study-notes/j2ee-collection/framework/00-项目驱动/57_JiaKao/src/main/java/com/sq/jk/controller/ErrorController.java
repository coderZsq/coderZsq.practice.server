package com.sq.jk.controller;

import com.sq.jk.filter.ErrorFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {
    @RequestMapping(ErrorFilter.ERROR_URI)
    public void handle(HttpServletRequest request) throws Exception {
        // 抛出异常
        throw (Exception) request.getAttribute(ErrorFilter.ERROR_URI);
    }
}

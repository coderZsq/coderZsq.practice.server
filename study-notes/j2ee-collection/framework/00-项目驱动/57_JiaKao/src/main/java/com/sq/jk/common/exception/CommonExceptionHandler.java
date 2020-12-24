package com.sq.jk.common.exception;

import com.sq.jk.common.util.Rs;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public void handle(Throwable t,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        response.setStatus(400);
        response.getWriter().write(Rs.error(t).json());
    }
}

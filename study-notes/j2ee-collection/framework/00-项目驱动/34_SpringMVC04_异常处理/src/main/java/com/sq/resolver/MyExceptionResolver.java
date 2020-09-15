package com.sq.resolver;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class MyExceptionResolver {
    @ExceptionHandler({ArithmeticException.class, IOException.class})
    public ModelAndView resolveException1(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("ex", ex);
        mv.setViewName("/WEB-INF/page/error/runtime.jsp");
        return mv;
    }

    @ExceptionHandler
    public ModelAndView resolveException2(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("ex", ex);
        mv.setViewName("/WEB-INF/page/error/default.jsp");
        return mv;
    }
}

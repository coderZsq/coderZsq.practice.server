package com.sq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("name", "Larry");
        model.addAttribute("age", 22);
        return "03_literal";
    }

    @GetMapping("/comment")
    public String comment(Model model) {
        return "02_comment";
    }

    @GetMapping("/helloworld")
    public String helloworld(Model model) {
        model.addAttribute("name", "Larry");
        model.addAttribute("age", 22);
        model.addAttribute("msg", "<h1>msg</h1>");
        return "01_helloworld";
    }

    // @GetMapping("/testIndex")
    // public String testIndex(HttpServletRequest request) {
    //     // prefix: classpath:/templates/
    //     // suffix: .html
    //     // classpath:/templates/01_helloworld.html
    //     request.setAttribute("name", "coderZsq");
    //     request.setAttribute("age", 20);
    //     return "index";
    // }

    // @GetMapping("/testIndex")
    // public ModelAndView testIndex() {
    //     // prefix: classpath:/templates/
    //     // suffix: .html
    //     // classpath:/templates/01_helloworld.html
    //     ModelAndView mv = new ModelAndView("index");
    //     mv.addObject("name", "coderZsq");
    //     mv.addObject("age", 20);
    //     return mv;
    // }
}

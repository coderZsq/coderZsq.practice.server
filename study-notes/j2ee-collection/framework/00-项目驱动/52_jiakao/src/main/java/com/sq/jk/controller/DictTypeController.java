package com.sq.jk.controller;

import com.sq.jk.query.DictTypeQuery;
import com.sq.jk.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dictTypes")
public class DictTypeController {
    @Autowired
    private DictTypeService service;

    @GetMapping("/list")
    public String list(DictTypeQuery query, Model model) {
        model.addAttribute("data", service.list(query));
        // classpath:/templates/
        // .ftlh
        return "pages/dictType";
    }
}

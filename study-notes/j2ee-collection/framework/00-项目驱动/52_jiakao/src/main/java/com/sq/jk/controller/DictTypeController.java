package com.sq.jk.controller;

import com.sq.jk.pojo.po.DictType;
import com.sq.jk.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dictTypes")
public class DictTypeController {
    @Autowired
    private DictTypeService service;

    @GetMapping("/list")
    @ResponseBody
    public List<DictType> list() {
        return service.list();
    }
}

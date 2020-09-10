package com.sq.demo.xatransdemo.web;

import com.sq.demo.xatransdemo.service.TransService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TransController {
    @Resource
    private TransService transService;

    @RequestMapping("/trans")
    public String trans(int transIn, int transOut, int amount) {
        transService.trans(transIn, transOut, amount);
        return "success";
    }
}

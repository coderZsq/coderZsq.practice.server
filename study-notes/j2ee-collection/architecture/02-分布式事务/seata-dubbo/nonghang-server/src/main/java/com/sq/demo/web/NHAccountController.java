package com.sq.demo.web;

import demo.service.INHAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class NHAccountController {

    private INHAccountService nhAccountService;

    @RequestMapping("trans")
    public String handler(Long outId, Long inId, Integer amount) throws Exception {
        nhAccountService.trans(outId, inId, amount);
        return "success";
    }
}

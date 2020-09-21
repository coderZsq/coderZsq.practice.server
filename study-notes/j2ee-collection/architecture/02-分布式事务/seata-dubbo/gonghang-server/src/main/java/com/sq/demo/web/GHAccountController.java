package com.sq.demo.web;

import demo.service.IGHAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class GHAccountController {

    private IGHAccountService ghAccountService;

    @RequestMapping("trans")
    public String trans(Long id, Integer amount){
        ghAccountService.transIn(id, amount);
        return "success";
    }
}

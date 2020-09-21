package com.maoge.demo.web;

import com.maoge.demo.domain.Account;
import com.maoge.demo.feign.AccountFeign;
import com.maoge.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountController implements AccountFeign {

    private AccountService accountService;

    public String reduce(Integer id, Integer amount){
        try{
             accountService.reduce(id, amount);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping(value = "account/query")
    public Account query(Integer id){
        try{
            return accountService.queryById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

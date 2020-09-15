package com.gonghang.trans.web;

import com.gonghang.trans.service.ITransService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransController {
    private ITransService transService;
    //提供接口--> ATM
    @RequestMapping("trans")
    public String trans(Integer inId,Integer outId,Integer amount){
        //调用转账服务
        transService.trans(inId, outId, amount);
        return "success";
    }
 
}

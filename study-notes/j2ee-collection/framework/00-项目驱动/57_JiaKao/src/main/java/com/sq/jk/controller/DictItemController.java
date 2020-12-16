package com.sq.jk.controller;

import com.sq.jk.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictItems")
public class DictItemController {
    @Autowired
    private DictItemService service;

}
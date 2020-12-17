package com.sq.jk.controller;

import com.sq.jk.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private ProvinceService service;

}
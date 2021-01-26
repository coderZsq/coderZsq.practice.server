package com.sq.jk.controller;

import com.sq.jk.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysResources")
public class SysResourceController {
    @Autowired
    private SysResourceService service;

}
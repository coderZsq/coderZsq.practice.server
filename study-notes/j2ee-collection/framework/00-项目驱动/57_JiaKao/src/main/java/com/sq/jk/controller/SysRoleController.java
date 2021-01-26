package com.sq.jk.controller;

import com.sq.jk.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysRoles")
public class SysRoleController {
    @Autowired
    private SysRoleService service;

}
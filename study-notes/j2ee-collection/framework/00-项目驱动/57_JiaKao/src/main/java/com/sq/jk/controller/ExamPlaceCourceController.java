package com.sq.jk.controller;

import com.sq.jk.service.ExamPlaceCourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examPlaceCources")
public class ExamPlaceCourceController {
    @Autowired
    private ExamPlaceCourceService service;

}
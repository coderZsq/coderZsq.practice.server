package com.sq.controller;


import com.sq.domain.Skill;
import com.sq.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService service;

    @GetMapping("/list")
    @ResponseBody
    public List<Skill> list() {
        return service.list();
    }

    @GetMapping("/get")
    @ResponseBody
    public Skill get(Integer id) {
        return service.get(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(Skill skill) {
        return service.save(skill) ? "保存成功" : "保存失败";
    }

    @PostMapping("/remove")
    @ResponseBody
    public String remove(Integer id) {
        return service.remove(id) ? "删除成功" : "删除失败";
    }
}

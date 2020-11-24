package com.sq.mvc.controller;

import com.sq.domain.Skill;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @GetMapping("/{id}")
    public Skill get(@PathVariable("id") Integer id) {
        // service.get(id);
        System.out.println("Get------");
        return null;
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Integer id) {
        // service.remove(id);
        return "删除成功!";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Integer id, Skill skill) {
        // skill.setId(id);
        // service.update(skill);
        return "更新成功";
    }

    @PostMapping("/")
    public String save(Skill skill) {
        // service.save(skill);
        return "添加成功";
    }

    @GetMapping("/")
    public List<Skill> list() {
        // service.list();
        return null;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/page/index.jsp");
        mv.addObject("name", "sq");
        return mv;
    }
}

package com.sq.controller;

import com.sq.domain.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/skill")
public class SkillController {
    // @RequestMapping("/add")
    // @ResponseBody
    // public String add(HttpServletRequest request,
    //                   HttpServletResponse response,
    //                   HttpSession session,
    //                   WebRequest request2) {
    //     System.out.println(request);
    //     System.out.println(response);
    //     System.out.println(session);
    //     return "SkillController - Add Success!";
    // }

    // @RequestMapping("/add")
    // @ResponseBody
    // public String add(@RequestParam(value = "my_level", required = false) Integer level,
    //                   @RequestParam String name,
    //                   @RequestParam String intro) {
    //     System.out.println(name);
    //     System.out.println(intro);
    //     System.out.println(level);
    //     return "SkillController - Add Success!";
    // }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Skill skill, String name, Integer level, Skill skill2) {
        System.out.println(skill);
        System.out.println(skill2);
        System.out.println(name);
        System.out.println(level);
        return "SkillController - Add Success!";
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public String get(@PathVariable("id") Integer id) {
        return "SkillController - Get Success! - " + id;
    }
}

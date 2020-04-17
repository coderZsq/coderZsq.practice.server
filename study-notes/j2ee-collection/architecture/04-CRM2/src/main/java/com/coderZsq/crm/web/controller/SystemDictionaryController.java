package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.SystemDictionary;
import com.coderZsq.crm.service.SystemDictionaryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.query.SystemDictionaryQueryObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Systemdictionary)表控制层
 *
 * @author makejava
 * @since 2020-03-27 11:28:46
 */
@Controller
@RequestMapping("systemDictionary")
public class SystemDictionaryController {
    /**
     * 服务对象
     */
    @Autowired
    private SystemDictionaryService systemdictionaryService;
	
	
    @RequestMapping("/list")
    public String list(Model model, SystemDictionaryQueryObject qo){
        model.addAttribute("pageInfo",systemdictionaryService.query(qo));
        model.addAttribute("qo",qo);
        return "systemDictionary/list";
    }
	
	@RequestMapping("/input")
    public String input(Model model,Long id){
        if(id!=null){
            SystemDictionary systemdictionary = systemdictionaryService.queryById(id);
            model.addAttribute("systemDictionary",systemdictionary);
        }
        return "systemDictionary/input";
    }
	
	
	@RequestMapping("/saveOrUpdate")
    @ResponseBody
    public PageResult saveOrUpdate(SystemDictionary systemdictionary){
        if(systemdictionary!=null&& systemdictionary.getId()!=null){
            systemdictionaryService.update(systemdictionary);
        }else{
            systemdictionaryService.insert(systemdictionary);
        }
        return PageResult.success();
    }
	
	@RequestMapping("/delete")
	@ResponseBody
    public PageResult delete(Long id){
        if(id!=null){
            systemdictionaryService.deleteById(id);
        }
        return PageResult.success();
    }
}
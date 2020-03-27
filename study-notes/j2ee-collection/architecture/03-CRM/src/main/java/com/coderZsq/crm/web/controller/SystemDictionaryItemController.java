package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.domain.SystemDictionaryItem;
import com.coderZsq.crm.service.SystemDictionaryItemService;
import com.coderZsq.crm.service.SystemDictionaryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.query.SystemDictionaryItemQueryObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Systemdictionaryitem)表控制层
 *
 * @author makejava
 * @since 2020-03-27 11:56:09
 */
@Controller
@RequestMapping("systemDictionaryItem")
public class SystemDictionaryItemController {
    /**
     * 服务对象
     */
    @Autowired
    private SystemDictionaryItemService systemdictionaryitemService;

    @Autowired
    private SystemDictionaryService systemDictionaryService;
	
    @RequestMapping("/list")
    public String list(Model model, SystemDictionaryItemQueryObject qo){
        model.addAttribute("pageInfo",systemdictionaryitemService.query(qo));
        model.addAttribute("qo",qo);
        // 查询字典目录的所有数据
        model.addAttribute("dics", systemDictionaryService.queryAll());
        return "systemDictionary/item";
    }
	
	@RequestMapping("/input")
    public String input(Model model,Long id){
        if(id!=null){
            SystemDictionaryItem systemdictionaryitem = systemdictionaryitemService.queryById(id);
            model.addAttribute("systemDictionaryItem",systemdictionaryitem);
        }
        return "systemDictionary/input";
    }
	
	
	@RequestMapping("/saveOrUpdate")
    @ResponseBody
    public PageResult saveOrUpdate(SystemDictionaryItem systemdictionaryitem){
        if(systemdictionaryitem!=null&& systemdictionaryitem.getId()!=null){
            systemdictionaryitemService.update(systemdictionaryitem);
        }else{
            systemdictionaryitemService.insert(systemdictionaryitem);
        }
        return PageResult.success();
    }
	
	@RequestMapping("/delete")
	@ResponseBody
    public PageResult delete(Long id){
        if(id!=null){
            systemdictionaryitemService.deleteById(id);
        }
        return PageResult.success();
    }
}
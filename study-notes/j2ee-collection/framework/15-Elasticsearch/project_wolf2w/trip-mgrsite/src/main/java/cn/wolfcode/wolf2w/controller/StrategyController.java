package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyContent;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.IStrategyThemeService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("strategy")
public class StrategyController {


    @Autowired
    private IStrategyThemeService strategyThemeService;

    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/list")
    public String list(Model model,  @ModelAttribute("qo") StrategyQuery qo){
        //page
        IPage<Strategy> page = strategyService.queryPage(qo);
        model.addAttribute("page", page);
        return "/strategy/list";
    }


    @RequestMapping("/input")
    public String input(Model model,  Long id){
        //strategy

        if(id != null){
            Strategy strategy = strategyService.getById(id);
            StrategyContent content = strategyService.getContent(id);
            strategy.setContent(content);
            model.addAttribute("strategy", strategy);
        }



        //catalogs
        model.addAttribute("catalogs", strategyCatalogService.queryGroupList());

        //themes
        model.addAttribute("themes", strategyThemeService.list());
        return "/strategy/input";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Strategy strategy){
        strategyService.saveOrUpdate(strategy);
        return JsonResult.success();
    }

}

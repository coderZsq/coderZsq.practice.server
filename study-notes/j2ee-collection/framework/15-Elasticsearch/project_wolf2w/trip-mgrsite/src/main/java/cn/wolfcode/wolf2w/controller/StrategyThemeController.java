package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.StrategyTheme;
import cn.wolfcode.wolf2w.query.StrategyThemeQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyThemeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("strategyTheme")
public class StrategyThemeController {

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IStrategyThemeService strategyThemeService;

    @RequestMapping("/list")
    public String list(Model model,  @ModelAttribute("qo") StrategyThemeQuery qo){
        //page
        IPage<StrategyTheme> page = strategyThemeService.queryPage(qo);
        model.addAttribute("page", page);
        return "/strategyTheme/list";
    }
}

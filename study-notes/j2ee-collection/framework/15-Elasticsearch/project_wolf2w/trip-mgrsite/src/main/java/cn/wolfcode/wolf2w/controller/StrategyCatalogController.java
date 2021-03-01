package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import cn.wolfcode.wolf2w.query.StrategyCatalogQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("strategyCatalog")
public class StrategyCatalogController {

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @RequestMapping("/list")
    public String list(Model model,  @ModelAttribute("qo") StrategyCatalogQuery qo){
        //page
        IPage<StrategyCatalog> page = strategyCatalogService.queryPage(qo);
        model.addAttribute("page", page);
        return "/strategyCatalog/list";
    }
}

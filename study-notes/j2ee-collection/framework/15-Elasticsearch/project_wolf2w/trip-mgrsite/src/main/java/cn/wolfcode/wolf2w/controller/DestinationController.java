package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IDestinationService;
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
@RequestMapping("destination")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;


    @RequestMapping("/list")
    public String list(Model model,  @ModelAttribute("qo") DestinationQuery qo){
        //page
        IPage<Destination> page = destinationService.queryPage(qo);
        model.addAttribute("page", page);
        //toasts  list<Destination>
        model.addAttribute("toasts", destinationService.queryToasts(qo.getParentId()));
        return "/destination/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Destination destination){
        destinationService.saveOrUpdate(destination);
        return JsonResult.success();
    }




    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        destinationService.removeById(id);
        return JsonResult.success();
    }

}

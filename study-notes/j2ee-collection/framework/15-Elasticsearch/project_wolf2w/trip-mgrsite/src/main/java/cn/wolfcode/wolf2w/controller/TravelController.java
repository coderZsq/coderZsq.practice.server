package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.TravelContent;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* 游记控制层
*/
@Controller
@RequestMapping("travel")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") TravelQuery qo){
        IPage<Travel> page = travelService.queryPage(qo);
        model.addAttribute("page", page);
        return "travel/list";
    }

    @RequestMapping("/getContentById")
    @ResponseBody
    public Object getContentById(Long id){
        TravelContent content = travelService.getContent(id);
        return JsonResult.success(content.getContent());
    }

    @RequestMapping("/audit")
    @ResponseBody
    public Object audit(Long id, int state){
         travelService.audit(id, state);
        return JsonResult.success();
    }




    @RequestMapping("/get")
    @ResponseBody
    public Object get(Long id){
        return JsonResult.success(travelService.getById(id));
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Travel travel){
        travelService.saveOrUpdate(travel);
        return JsonResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        travelService.removeById(id);
        return JsonResult.success();
    }
}

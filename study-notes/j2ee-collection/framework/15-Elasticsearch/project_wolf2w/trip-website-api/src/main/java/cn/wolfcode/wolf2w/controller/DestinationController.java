package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.service.*;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("destinations")
public class DestinationController {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IDestinationService destinationService;
    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private ITravelService travelService;

    @GetMapping("/hotRegion")
    public Object hotRegion(){
        List<Region> list = regionService.queryHotRegion();
        return JsonResult.success(list);
    }

    @GetMapping("/search")
    public Object search(Long regionId){
        List<Destination> list = destinationService.queryByRegionIdForApi(regionId);
        return JsonResult.success(list);
    }

    @GetMapping("/toasts")
    public Object toasts(Long destId){
        return JsonResult.success(destinationService.queryToasts(destId));
    }

    @GetMapping("/catalogs")
    public Object catalogs(Long destId){
        //List<StrategyCatalog> list
        return JsonResult.success(strategyCatalogService.queryByDestId(destId));
    }

    @GetMapping("/strategies/viewnumTop3")
    public Object stViewnumTop3(Long destId){
        return JsonResult.success(strategyService.queryViewnumTop3(destId));
    }
    @GetMapping("/travels/viewnumTop3")
    public Object tvViewnumTop3(Long destId){
        return JsonResult.success(travelService.queryViewnumTop3(destId));
    }
}

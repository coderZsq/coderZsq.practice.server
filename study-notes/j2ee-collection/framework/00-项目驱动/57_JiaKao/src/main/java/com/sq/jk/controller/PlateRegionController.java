package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.po.PlateRegion;
import com.sq.jk.pojo.query.CityQuery;
import com.sq.jk.pojo.query.ProvinceQuery;
import com.sq.jk.pojo.result.R;
import com.sq.jk.service.PlateRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plateRegions")
public class PlateRegionController extends BaseController<PlateRegion> {
    @Autowired
    private PlateRegionService service;

    @GetMapping("/provinces")
    public R listProvinces(ProvinceQuery query) {
        service.listProvinces(query);
        return Rs.ok(query);
    }

    @GetMapping("/cities")
    public R listCities(CityQuery query) {
        service.listCities(query);
        return Rs.ok(query);
    }

    @Override
    protected IService<PlateRegion> getService() {
        return service;
    }
}
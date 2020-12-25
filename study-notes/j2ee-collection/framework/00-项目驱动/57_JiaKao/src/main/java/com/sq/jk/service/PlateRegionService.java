package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.PlateRegion;
import com.sq.jk.pojo.query.CityQuery;
import com.sq.jk.pojo.query.ProvinceQuery;

import java.util.List;

public interface PlateRegionService extends IService<PlateRegion> {

    void listProvinces(ProvinceQuery query);

    void listCities(CityQuery query);

    List<PlateRegion> listProvinces();
}
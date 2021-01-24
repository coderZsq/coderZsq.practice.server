package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.PlateRegion;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.PlateRegionVo;
import com.sq.jk.pojo.vo.list.ProvinceVo;
import com.sq.jk.pojo.vo.req.page.CityPageReqVo;
import com.sq.jk.pojo.vo.req.page.ProvincePageReqVo;

import java.util.List;

public interface PlateRegionService extends IService<PlateRegion> {
    List<ProvinceVo> listRegions();

    PageVo<PlateRegionVo> listProvinces(ProvincePageReqVo query);

    PageVo<PlateRegionVo> listCities(CityPageReqVo query);

    List<PlateRegionVo> listProvinces();
}
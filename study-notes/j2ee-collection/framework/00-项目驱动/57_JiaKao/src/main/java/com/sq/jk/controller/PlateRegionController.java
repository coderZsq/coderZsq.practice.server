package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.PlateRegion;
import com.sq.jk.pojo.vo.DataJsonVo;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.PlateRegionVo;
import com.sq.jk.pojo.vo.list.ProvinceVo;
import com.sq.jk.pojo.vo.req.page.CityPageReqVo;
import com.sq.jk.pojo.vo.req.page.ProvincePageReqVo;
import com.sq.jk.pojo.vo.req.save.PlateRegionReqVo;
import com.sq.jk.service.PlateRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/plateRegions")
@Api(tags = "省份/城市")
public class PlateRegionController extends BaseController<PlateRegion, PlateRegionReqVo> {
    @Autowired
    private PlateRegionService service;

    @GetMapping("/regions")
    @ApiOperation("查询所有的区域 (省份, 城市)")
    @RequiresPermissions(value = {
            Constants.Permission.PROVINCE_LIST,
            Constants.Permission.CITY_LIST
    }, logical = Logical.AND)
    public DataJsonVo<List<ProvinceVo>> listRegions() {
        return JsonVos.ok(service.listRegions());
    }

    @GetMapping("/provinces")
    @ApiOperation("分页查询省份")
    @RequiresPermissions(Constants.Permission.PROVINCE_LIST)
    public PageJsonVo<PlateRegionVo> listProvinces(ProvincePageReqVo query) {
        return JsonVos.ok(service.listProvinces(query));
    }

    @GetMapping("/provinces/list")
    @ApiOperation("查询所有的省份")
    @RequiresPermissions(Constants.Permission.PROVINCE_LIST)
    public DataJsonVo<List<PlateRegionVo>> listProvinces() {
        return JsonVos.ok(service.listProvinces());
    }

    @GetMapping("/cities")
    @ApiOperation("分页查询城市")
    @RequiresPermissions(Constants.Permission.CITY_LIST)
    public PageJsonVo<PlateRegionVo> listCities(CityPageReqVo query) {
        return JsonVos.ok(service.listCities(query));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.PROVINCE_ADD,
            Constants.Permission.PROVINCE_UPDATE,
            Constants.Permission.CITY_ADD,
            Constants.Permission.CITY_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(PlateRegionReqVo plateRegionReqVo) {
        return super.save(plateRegionReqVo);
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.PROVINCE_REMOVE,
            Constants.Permission.CITY_REMOVE
    }, logical = Logical.AND)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @Override
    protected IService<PlateRegion> getService() {
        return service;
    }

    @Override
    protected Function<PlateRegionReqVo, PlateRegion> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}
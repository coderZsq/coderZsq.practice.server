package com.sq.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.jk.pojo.dto.ProvinceDto;
import com.sq.jk.pojo.po.PlateRegion;

import java.util.List;

public interface PlateRegionMapper extends BaseMapper<PlateRegion> {
    List<ProvinceDto> selectRegions();
}
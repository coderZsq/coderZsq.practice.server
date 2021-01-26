package com.sq.jk.common.mapStruct;

import com.sq.jk.pojo.po.*;
import com.sq.jk.pojo.vo.LoginVo;
import com.sq.jk.pojo.vo.list.*;
import com.sq.jk.pojo.vo.req.save.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ReqVo -> Po
 * Po -> Vo
 */
@Mapper(uses = {
        MapStructFormatter.class
})
public interface MapStructs {
    MapStructs INSTANCE = Mappers.getMapper(MapStructs.class);

    DictItemVo po2vo(DictItem po);
    DictTypeVo po2vo(DictType po);
    ExamPlaceVo po2vo(ExamPlace po);
    PlateRegionVo po2vo(PlateRegion po);
    ExamPlaceCourseVo po2vo(ExamPlaceCourse po);
    @Mapping(source = "loginTime",
            target = "loginTime",
            qualifiedBy = MapStructFormatter.Date2Millis.class)
    SysUserVo po2vo(SysUser po);
    LoginVo po2loginVo(SysUser po);
    SysRoleVo po2vo(SysRole po);
    SysResourceVo po2vo(SysResource po);

    DictItem reqVo2po(DictItemReqVo reqVo);
    DictType reqVo2po(DictTypeReqVo reqVo);
    ExamPlace reqVo2po(ExamPlaceReqVo reqVo);
    PlateRegion reqVo2po(PlateRegionReqVo reqVo);
    ExamPlaceCourse reqVo2po(ExamPlaceCourseReqVo reqVo);
    SysUser reqVo2po(SysUserReqVo reqVo);
    SysRole reqVo2po(SysRoleReqVo reqVo);
    SysResource reqVo2po(SysResourceReqVo reqVo);
}

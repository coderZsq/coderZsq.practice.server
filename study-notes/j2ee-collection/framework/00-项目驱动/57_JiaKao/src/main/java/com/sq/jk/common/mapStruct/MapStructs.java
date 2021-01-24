package com.sq.jk.common.mapStruct;

import com.sq.jk.pojo.po.*;
import com.sq.jk.pojo.vo.list.*;
import com.sq.jk.pojo.vo.req.save.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ReqVo -> Po
 * Po -> Vo
 */
@Mapper
public interface MapStructs {
    MapStructs INSTANCE = Mappers.getMapper(MapStructs.class);

    DictItemVo po2vo(DictItem po);
    DictTypeVo po2vo(DictType po);
    ExamPlaceVo po2vo(ExamPlace po);
    PlateRegionVo po2vo(PlateRegion po);
    ExamPlaceCourseVo po2vo(ExamPlaceCourse po);

    DictItem reqVo2po(DictItemReqVo reqVo);
    DictType reqVo2po(DictTypeReqVo reqVo);
    ExamPlace reqVo2po(ExamPlaceReqVo reqVo);
    PlateRegion reqVo2po(PlateRegionReqVo reqVo);
    ExamPlaceCourse reqVo2po(ExamPlaceCourseReqVo reqVo);
}

package com.sq.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("科2科3课程")
public class ExamPlaceCourseVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("课程类型 [0是课程合集, 2是科目2, 3是科目3]")
    private Short type;

    @ApiModelProperty("简介")
    private String intro;

    @ApiModelProperty("考场id")
    private Integer placeId;

    @ApiModelProperty("省份id")
    private Integer provinceId;

    @ApiModelProperty("城市id")
    private Integer cityId;
}
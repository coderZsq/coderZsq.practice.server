package com.sq.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ExamPlaceCourseReqVo {
    @ApiModelProperty("id [大于0代表更新, 否则代表添加]")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称 [不能为空]", required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;

    @NotNull
    @ApiModelProperty(value = "课程类型 [0是课程合集, 2是科目2, 3是科目3]", required = true)
    private Short type;

    @ApiModelProperty("简介")
    private String intro;

    @NotNull
    @ApiModelProperty(value = "考场id", required = true)
    private Integer placeId;
}
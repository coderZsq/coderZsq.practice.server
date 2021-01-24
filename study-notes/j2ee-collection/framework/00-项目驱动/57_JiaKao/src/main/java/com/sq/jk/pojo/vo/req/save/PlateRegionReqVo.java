package com.sq.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlateRegionReqVo {
    @ApiModelProperty("id [大于0代表更新, 否则代表添加]")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称 [不能为空]", required = true)
    private String name;

    @NotNull
    @Length(min = 1, max = 1, message = "车牌的长度只能是1")
    @ApiModelProperty(value = "车牌 [车牌的长度只能是1, 比如粤, 黑, A, B等]", required = true)
    private String plate;

    @ApiModelProperty("父区域id [如果是城市, 父区域id大于0; 如果是省份, 父区域id是0]; 默认0")
    private Integer parentId;
}
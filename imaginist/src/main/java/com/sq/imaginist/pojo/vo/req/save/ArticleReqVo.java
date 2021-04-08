package com.sq.imaginist.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ArticleReqVo {
    @ApiModelProperty("id [大于0代表更新, 否则代表添加]")
    private Integer id;

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题 [不能为空]", required = true)
    private String title;

    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "类型 [不能为空]", required = true)
    private String type;

    @NotBlank(message = "正文不能为空")
    @ApiModelProperty(value = "正文 [不能为空]", required = true)
    private String content;

    @NotBlank(message = "预览不能为空")
    @ApiModelProperty(value = "预览 [不能为空]", required = true)
    private String preview;

    @Min(value = 0, message = "字数不能是负数")
    @ApiModelProperty(value = "字数 [不能是负数]", required = true)
    private Integer words;

    @Min(value = 0, message = "阅读时长不能是负数")
    @ApiModelProperty(value = "阅读时长 [不能是负数]", required = true)
    private Integer duration;

    @Min(value = 0, message = "日期不能是负数")
    @ApiModelProperty(value = "日期 [不能是负数]", required = true)
    private Long date;
}
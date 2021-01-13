package com.sq.jk.pojo.result;

import com.sq.jk.pojo.po.DictItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("DictItem列表数据")
@Data
public class SwaggerDictItemR {
    @ApiModelProperty("正确代码 (固定是0)")
    private int code;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("具体数据")
    private List<DictItem> data;
}

package com.sq.jk.pojo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录成功的结果")
public class LoginVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("昵称")
    private Integer nickname;

    @ApiModelProperty("用户名")
    private Integer username;

    // 1.57.43
}

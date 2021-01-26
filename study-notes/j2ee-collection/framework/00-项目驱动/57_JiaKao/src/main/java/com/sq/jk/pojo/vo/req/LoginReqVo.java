package com.sq.jk.pojo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReqVo {
    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotBlank
    @ApiModelProperty(value = "验证码", required = true)
    private String captcha;
}

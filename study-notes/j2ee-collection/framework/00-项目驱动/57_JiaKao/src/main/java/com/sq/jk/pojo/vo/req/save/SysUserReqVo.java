package com.sq.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SysUserReqVo {
    @ApiModelProperty("id [大于0代表更新, 否则代表添加]")
    private Integer id;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称 [不能为空]", required = true)
    private String nickname;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名 [不能为空]", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码 [不能为空]", required = true)
    private String password;

    @Range(min = 0, max = 1, message = "状态只能是0和1")
    @ApiModelProperty("状态 [0是正常, 1是锁定, 默认0]")
    private Short status;
}
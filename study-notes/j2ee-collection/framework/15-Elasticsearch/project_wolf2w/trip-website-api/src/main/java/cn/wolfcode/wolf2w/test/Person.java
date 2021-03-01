package cn.wolfcode.wolf2w.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户",description="平台注册用户模型")
public class Person {
    @ApiModelProperty(value="昵称",name="username",dataType = "String",required = true)
    private String username;
    @ApiModelProperty(value="密码",name="password",dataType = "String",required = true)
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

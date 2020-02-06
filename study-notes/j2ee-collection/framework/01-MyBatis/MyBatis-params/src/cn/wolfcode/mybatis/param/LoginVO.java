package cn.wolfcode.mybatis.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

//封装登录的信息
@AllArgsConstructor
@Getter
public class LoginVO {
	private String username;
	private String password;
}

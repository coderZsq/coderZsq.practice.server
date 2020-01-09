package cn.wolfcode.register.action;

import cn.wolfcode.register.domain.User;
import cn.wolfcode.register.service.IUserService;
import lombok.Setter;

//模拟Struts2的Action/SpringMVC的Controller
public class UserAction {

	@Setter
	private IUserService service;

	public String execute() {
		System.out.println("注册请求");
		service.register(new User());
		return "success";
	}
}

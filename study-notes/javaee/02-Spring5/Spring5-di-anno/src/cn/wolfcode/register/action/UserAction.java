package cn.wolfcode.register.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wolfcode.register.domain.User;
import cn.wolfcode.register.service.IUserService;
import lombok.Setter;

//模拟Struts2的Action/SpringMVC的Controller
@Controller
@Scope("prototype")
public class UserAction {

	@Autowired
	private IUserService service;

	public String execute() {
		System.out.println("注册请求");
		service.register(new User());
		return "success";
	}
}

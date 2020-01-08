package cn.wolfcode.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wolfcode.service.IEmployeeService;

@Controller
public class LoginController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("/login")
	public String login(String username, String password, HttpSession session) {
		try {
			employeeService.login(username, password);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.getMessage());
			return "redirect:/login.jsp";
		}
		return "redirect:/employee/list";
	}
}

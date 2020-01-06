package cn.wolfcode.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("employees", employeeService.listAll());
		return "employee/list";
	}

	@RequestMapping("/input")
	public String input(Model model,Long id) {
		if(id!=null) {
			model.addAttribute("employee",employeeService.get(id));
		}
		return "employee/input";
	}

	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Employee e) {
		if (e.getId() == null) {
			employeeService.save(e);
		} else {
			employeeService.update(e);
		}
		return "redirect:/employee/list";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		if (id != null) {
			employeeService.delete(id);
		}
		return "redirect:/employee/list";
	}
}

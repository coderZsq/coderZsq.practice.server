package cn.wolfcode.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("employee", employeeService.get(id));
		}else {
			model.addAttribute("employee", new Employee());
		}
		return "employee/input";
	}

	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(@Valid Employee e, BindingResult bindingResult, Model model) {
		List<ObjectError> errors = bindingResult.getAllErrors();
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "employee/input";
		}
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

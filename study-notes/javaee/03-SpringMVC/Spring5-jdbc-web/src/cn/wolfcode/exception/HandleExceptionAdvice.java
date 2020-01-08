package cn.wolfcode.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//处理异常
@ControllerAdvice
public class HandleExceptionAdvice {
	
	@ExceptionHandler
	public String error(Exception ex, Model model) {
		model.addAttribute("errorMsg", ex.getMessage());
		return "commons/error";
	}
}

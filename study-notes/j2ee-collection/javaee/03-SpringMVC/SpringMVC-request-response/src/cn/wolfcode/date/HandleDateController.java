package cn.wolfcode.date;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.wolfcode.vo.User;

@Controller
@RequestMapping("/date")
public class HandleDateController {

	//从前天--->后台传递参数:String----->java.util.Date
	//把请求参数封装成Date类型
	@RequestMapping("/test1")
	public ModelAndView test1(@DateTimeFormat(pattern = "yyyy-MM-dd") Date d) {
		System.out.println(d);
		return null;
	}

	//把请求参数封装成Date类型
	@RequestMapping("/test2")
	public ModelAndView test2(User u) {
		System.out.println(u);
		return null;
	}

	@RequestMapping("/test3")
	@ResponseBody
	public User test3() {
		User u = new User();
		u.setUsername("will");
		u.setAge(17);
		u.setHiredate(new Date());
		return u;
	}

}

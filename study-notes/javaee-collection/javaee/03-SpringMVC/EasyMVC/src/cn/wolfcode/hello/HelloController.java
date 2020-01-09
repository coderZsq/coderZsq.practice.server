package cn.wolfcode.hello;

import cn.wolfcode.easymvc.annotation.Controller;
import cn.wolfcode.easymvc.annotation.RequestMapping;
import cn.wolfcode.easymvc.bean.ModelAndView;

/*
  	浏览器地址栏:  http://localhost:80/hello
  					执行HelloController类中的sayHello方法
  					跳转到/WEB-INF/views/welcome.jsp页面,并在该页面可以获取共享数据:${msg}
 
 */
//表示当前类是一个请求处理器
@Controller
public class HelloController {
	
	
	//给访问sayHello方法设置一个访问路径
	@RequestMapping("/hello")
	public ModelAndView sayHello() {
		System.out.println("sayHello......");
		//封装了模型(共享的数据)和视图(跳转的界面)
		ModelAndView mv = new ModelAndView();
		//设置逻辑视图名称
		mv.setViewName("welcome");
		//设置共享数据
		mv.addAttribute("msg", "你好EasyMVC1");
		return mv;
	}
	
	@RequestMapping("/hello2")
	public ModelAndView sayHello2() {
		System.out.println("sayHello......");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");
		mv.addAttribute("msg", "你好EasyMVC2");
		return mv;
	}
}

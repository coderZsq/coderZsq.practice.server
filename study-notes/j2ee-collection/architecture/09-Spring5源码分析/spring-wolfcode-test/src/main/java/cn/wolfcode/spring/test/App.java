package cn.wolfcode.spring.test;

import cn.wolfcode.spring.test.service.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Leon
 * @date 2021/3/16
 */
public class App {

	public static void main(String[] args) {
		// 基于 xml 配置文件的启动
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		IUserService service = (IUserService) ctx.getBean("userService");
		service.test();
		ctx.close();
	}
}

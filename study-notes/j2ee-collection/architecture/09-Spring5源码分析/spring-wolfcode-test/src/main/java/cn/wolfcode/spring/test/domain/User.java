package cn.wolfcode.spring.test.domain;

import cn.wolfcode.spring.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Leon
 * @date 2021/3/16
 */
@Component
public class User {

	private String test = "111";

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userServiceImpl;

	public User() {
		System.out.println("创建了 User:无参构造器......");
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		System.out.println("创建了 User:有参构造器......");
	}

	private String username;
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void init() {
		System.out.println("User 初始化了......");
	}

	public void destroy() {
		System.out.println("User 销毁了......");
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}' + "===>" + hashCode();
	}
}

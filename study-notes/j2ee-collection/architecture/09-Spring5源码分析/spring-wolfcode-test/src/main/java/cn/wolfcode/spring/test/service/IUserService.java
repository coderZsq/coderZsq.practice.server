package cn.wolfcode.spring.test.service;

/**
 * @author Leon
 * @date 2021/3/16
 */
public interface IUserService {

	void test();

	void login(String username, String password);

	void register(String mobile);
}

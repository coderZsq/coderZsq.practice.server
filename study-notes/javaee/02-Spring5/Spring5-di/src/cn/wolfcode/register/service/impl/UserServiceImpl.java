package cn.wolfcode.register.service.impl;

import cn.wolfcode.register.dao.IUserDAO;
import cn.wolfcode.register.domain.User;
import cn.wolfcode.register.service.IUserService;
import lombok.Setter;

public class UserServiceImpl implements IUserService {

	@Setter
	private IUserDAO dao;

	public void register(User user) {
		System.out.println("注册操作");
		dao.save(user);
	}

}

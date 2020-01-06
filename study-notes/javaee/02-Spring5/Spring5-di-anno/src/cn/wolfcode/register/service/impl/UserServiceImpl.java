package cn.wolfcode.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.register.dao.IUserDAO;
import cn.wolfcode.register.domain.User;
import cn.wolfcode.register.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO dao;

	public void register(User user) {
		System.out.println("注册操作");
		dao.save(user);
	}

}

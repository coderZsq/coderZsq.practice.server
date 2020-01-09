package cn.wolfcode.service.impl;

import cn.wolfcode.dao.IAccountDAO;
import cn.wolfcode.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

	private IAccountDAO dao;

	public void setDao(IAccountDAO dao) {
		this.dao = dao;
	}

	public void trans(Long outId, Long inId, int money) {
		dao.transOut(outId, money);
		//int a = 1 / 0;//模拟程序有异常
		dao.transIn(inId, money);
	}
}

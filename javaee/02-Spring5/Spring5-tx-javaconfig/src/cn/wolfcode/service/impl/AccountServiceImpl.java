package cn.wolfcode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wolfcode.dao.IAccountDAO;
import cn.wolfcode.service.IAccountService;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDAO dao;

	public void trans(Long outId, Long inId, int money) {
		dao.transOut(outId, money);
		int a = 1 / 0;//模拟程序有异常
		dao.transIn(inId, money);
	}
}

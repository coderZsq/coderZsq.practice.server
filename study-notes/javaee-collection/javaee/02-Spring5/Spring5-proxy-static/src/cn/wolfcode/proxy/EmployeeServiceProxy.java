package cn.wolfcode.proxy;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.tx.TransactionManager;

//静态代理类
public class EmployeeServiceProxy implements IEmployeeService {
	private IEmployeeService target;//真实对象/委托对象

	private TransactionManager txManager;//事务管理器

	public void setTarget(IEmployeeService target) {
		this.target = target;
	}

	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

	public void save(Employee emp) {
		txManager.begin();
		try {
			target.save(emp);
			txManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			txManager.rollback();
		}
	}

	public void update(Employee emp) {
		txManager.begin();
		try {
			target.update(emp);
			txManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			txManager.rollback();
		}
	}
}

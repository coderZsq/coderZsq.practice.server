package cn.wolfcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.wolfcode.service.IAccountService;

@SpringJUnitConfig
public class App {

	@Autowired
	private IAccountService service;

	@Test
	void testTrans() throws Exception {
		service.trans(10086L, 10010L, 1000);
	}
}

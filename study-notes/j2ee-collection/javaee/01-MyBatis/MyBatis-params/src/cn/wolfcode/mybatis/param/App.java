package cn.wolfcode.mybatis.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.param.domain.Client;
import cn.wolfcode.mybatis.param.mapper.ClientMapper;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	@Test
	void testLogin1() throws Exception {
		LoginVO vo = new LoginVO("will", "1111");
		SqlSession session = MyBatisUtil.getSession();
		ClientMapper clientMapper = session.getMapper(ClientMapper.class);
		Client client = clientMapper.login1(vo);
		session.close();
		System.out.println(client);
	}

	@Test
	void testLogin2() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>() {
			{
				this.put("username", "will");
				this.put("password", "1111");
			}
		};
		SqlSession session = MyBatisUtil.getSession();
		ClientMapper clientMapper = session.getMapper(ClientMapper.class);
		Client client = clientMapper.login2(paramMap);
		session.close();
		System.out.println(client);
	}
	@Test
	void testLogin3() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		ClientMapper clientMapper = session.getMapper(ClientMapper.class);
		Client client = clientMapper.login3("will","1111");
		session.close();
		System.out.println(client);
	}
	@Test
	void test4() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		ClientMapper clientMapper = session.getMapper(ClientMapper.class);
		List<Client> list = clientMapper.list("id desc");
		session.close();
		for (Client c : list) {
			System.out.println(c);
		}
	}
}

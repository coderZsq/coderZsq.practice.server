package cn.wolfcode.mybatis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.demo.domain.SystemUser;
import cn.wolfcode.mybatis.demo.domain.SystemUserExample;
import cn.wolfcode.mybatis.demo.domain.SystemUserExample.Criteria;
import cn.wolfcode.mybatis.demo.domain.SystemUserKey;
import cn.wolfcode.mybatis.demo.mapper.SystemUserMapper;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {
	
	
	@Test
	void test1() throws Exception {
		SystemUserMapper mapper = MyBatisUtil.getMapper(SystemUserMapper.class);
		
		SystemUserKey key = new SystemUserKey();
		key.setId(2L);
		//查询ID为2的数据
		mapper.selectByPrimaryKey(key);
		
		//查询userType在2~10之间的
		//封装查询条件
		SystemUserExample example = new SystemUserExample();
		//查询规则
		Criteria criteria = example.createCriteria();
		
		//criteria.andUserTypeGreaterThanOrEqualTo(2).andUserTypeLessThanOrEqualTo(10);
		criteria.andUserTypeBetween(2, 10);
		//查询userType在2~10之间的,且名中带有老字的
		criteria.andUsernameLike("%老%");
		mapper.selectByExample(example );
		
		
	}
}

package cn.wolfcode.mybatis.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.mybatis.param.LoginVO;
import cn.wolfcode.mybatis.param.domain.Client;

public interface ClientMapper {

	//方式一:把多个参数封装成JavaBean
	Client login1(LoginVO vo);

	//方式二:使用Map对象来封装多个参数
	Client login2(Map<String, Object> paramMap);

	//方式三:使用Param注解,原理是方式二,Param注解中的字符串表示在Map中的key
	Client login3(@Param("username")String username, @Param("password")String password);
	
	List<Client> list(@Param("orderBy") String orderBy);
}

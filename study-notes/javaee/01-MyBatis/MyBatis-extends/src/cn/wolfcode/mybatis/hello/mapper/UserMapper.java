package cn.wolfcode.mybatis.hello.mapper;

import java.util.List;

import cn.wolfcode.mybatis.hello.domain.User;

public interface UserMapper {
	//语句:cn.wolfcode.mybatis.hello.mapper.UserMapper.save
	void save(User u);

	void update(User u);

	void delete(Long id);

	User get(Long id);

	List<User> listAll();
}

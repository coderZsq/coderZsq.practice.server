package cn.wolfcode.mybatis.hello.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.wolfcode.mybatis.hello.domain.User;

public interface UserMapper {
	@Insert({ "INSERT INTO t_user (name,salary)", "VALUES(#{name},#{salary})" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(User u);

	@Update("UPDATE t_user SET name= #{name} ,salary = #{salary} WHERE id = #{id}")
	void update(User u);

	@Delete("DELETE FROM t_user WHERE id = #{id}")
	void delete(Long id);

	@Select("SELECT id AS u_id,name AS u_name,salary AS u_salary FROM t_user WHERE id = #{id}")
	@Results(id="BaseResultMap",value= {
			@Result(column="u_id",property="id"),
			@Result(column="u_name",property="name"),
			@Result(column="u_salary",property="salary")
	})
	User get(Long id);

	@Select("SELECT id AS u_id,name AS u_name,salary AS u_salary FROM t_user")
	@ResultMap("BaseResultMap")
	List<User> listAll();
}

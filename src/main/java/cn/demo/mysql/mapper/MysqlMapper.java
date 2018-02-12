package cn.demo.mysql.mapper;

import cn.demo.sysuser.domain.User;

import java.util.List;

public interface MysqlMapper {

	List<User> getAll();

	void save(User user);

	User get(Long id);
	
	void del(Long id);

	void update(User user);
}

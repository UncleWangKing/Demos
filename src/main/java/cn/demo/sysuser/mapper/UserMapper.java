package cn.demo.sysuser.mapper;

import java.util.List;

import cn.demo.sysuser.domain.User;

public interface UserMapper {

	List<User> getAll();

	void save(User user);

	User get(Long id);
	
	void del(Long id);

	void update(User user);
}

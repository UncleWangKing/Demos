package cn.demo.sysuser.service;

import java.util.List;

import cn.demo.sysuser.domain.User;

public interface IUserService {
	void save(User user);

	void saveTest(User user);

	User get(Long id);
	
	List<User> getAll();

	void del(Long id);

	void update(User user);
}

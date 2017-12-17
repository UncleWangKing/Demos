package cn.play.sysuser.service;

import java.util.List;

import cn.play.sysuser.domain.User;

public interface IUserService {
	void save(User user);

	User get(Long id);
	
	List<User> getAll();

	void del(Long id);

	void update(User user);
}

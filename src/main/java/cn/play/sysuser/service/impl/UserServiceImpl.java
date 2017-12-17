package cn.play.sysuser.service.impl;

import cn.play.sysuser.domain.User;
import cn.play.sysuser.mapper.UserMapper;
import cn.play.sysuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 事务传播属性默认值(propagation = Propagation.REQUIRED)
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Transactional(propagation= Propagation.REQUIRED)
	public void save(User user) {
		userMapper.save(user);
	}

	public User get(Long id) {
		return userMapper.get(id);
	}

	public List<User> getAll() {
		return userMapper.getAll();
	}

	@Transactional(propagation= Propagation.REQUIRED)
	public void del(Long id) {
		userMapper.del(id);
	}

	@Transactional(propagation= Propagation.REQUIRED)
	public void update(User user) {
		userMapper.update(user);
	}

}

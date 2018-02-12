package cn.demo.sysuser.service.impl;

import cn.demo.sysuser.domain.User;
import cn.demo.sysuser.mapper.UserMapper;
import cn.demo.sysuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
// 事务传播属性默认值(propagation = Propagation.REQUIRED)
//@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ApplicationContext context;

	private IUserService proxy;

	@PostConstruct
	public void init(){
		proxy = context.getBean(IUserService.class);
	}

	@Transactional(propagation= Propagation.REQUIRED)
	public void save(User user) {
		userMapper.save(user);
		try {
			//如果此处直接用this.saveTest(user); 将没有事务 因为使用的不是代理 事务由代理完成
			proxy.saveTest(user);
		}catch (RuntimeException e){
			System.out.println(e.getMessage());
		}
	}

//	@Transactional(propagation = Propagation.REQUIRED)//将会父子一起回滚  因为是一个事务
	@Transactional(propagation = Propagation.REQUIRES_NEW)//子会回滚 父不会 因为是两个事务
	public void saveTest(User user) {
		user.setUsername("事务2");
		user.setPassword("还是事务2");
		userMapper.save(user);
		throw new RuntimeException("saveTest 挂了");
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

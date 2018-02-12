package cn.demo.sysuser;

import cn.demo.sysuser.controller.SysUserController;
import cn.demo.sysuser.domain.User;
import cn.demo.sysuser.service.IUserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-test.xml")
public class SysUserTest extends TestCase {
	@Autowired
	IUserService userService;

	@Autowired
	SysUserController userController;
	@Test
	public void testList() throws Exception {
		List<User> list = userController.list();
		for (User user:list) {
			System.out.println(user);
		}
	}

	@Test
	public void testSave() throws Exception {
		User user = new User();
		user.setUsername("事务");
		user.setPassword("还是事务");
		userController.save(user);
	}
}

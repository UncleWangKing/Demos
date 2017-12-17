package cn.player.sysuser.test;

import cn.play.sysuser.controller.SysUserController;
import cn.play.sysuser.service.IUserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-test.xml")
public class SysUserTest extends TestCase {
	@Autowired
	IUserService userService;

	@Autowired
	SysUserController userController;
	@Test
	public void testController() throws Exception {
	    System.out.println(userController.list());
		System.out.println("dev");
	}
}

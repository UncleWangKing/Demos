package cn.demo.mysql;

import cn.demo.mysql.domain.MysqlBean;
import cn.demo.mysql.mapper.MysqlMapper;
import cn.demo.sysuser.domain.User;
import cn.demo.sysuser.mapper.UserMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-test.xml")
public class MysqlTest extends TestCase {
	@Autowired
	private MysqlMapper mysqlMapper;

	@Test
	public void testList() throws Exception {
		List<MysqlBean> list = mysqlMapper.getAll();
		for (MysqlBean user:list) {
			System.out.println(user);
		}
	}
}

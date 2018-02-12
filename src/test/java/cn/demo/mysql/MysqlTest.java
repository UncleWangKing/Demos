package cn.demo.mysql;

import cn.demo.mysql.domain.MysqlBean;
import cn.demo.mysql.mapper.MysqlMapper;
import cn.demo.mysql.util.UuidUtils;
import cn.demo.sysuser.domain.User;
import cn.demo.sysuser.mapper.UserMapper;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	@Test
	public void testBatchInsert() throws Exception {
		//插入500W条数据
		Random random = new Random();
		for (int time = 0; time < 500; time++) {
			List<MysqlBean> list = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				MysqlBean bean = new MysqlBean();
				bean.setUsername(UuidUtils.newUUIDString().substring(0, random.nextInt( 10) + 1));
				bean.setPassword(UuidUtils.newUUIDString().substring(0, random.nextInt( 10) + 1));
				bean.setUuid(UuidUtils.newUUIDString());
				list.add(bean);
			}
			System.out.println(mysqlMapper.batchInsert(list));
		}
	}
}

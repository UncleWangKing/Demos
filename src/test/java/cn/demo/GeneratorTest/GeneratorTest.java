package cn.demo.GeneratorTest;

import cn.generator.domain.Category;
import cn.generator.mapper.CategoryMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-test.xml")
public class GeneratorTest extends TestCase {
	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	public void testList() throws Exception {
		Category category = categoryMapper.selectByPrimaryKey(1);
		System.out.println(category);
	}
}

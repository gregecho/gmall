import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.greg.gmall.model.Category;
import com.greg.gmall.service.CategoryService;
import com.greg.gmall.service.imp.CategoryServiceImp;

/**
 * @Description TODO(采用Spring的注解调试，仅仅支持Spring3.1及以上)
 * @author greg li
 * 
 */
/*
 * Spring3.1后多了个spring-test-4.2.4.RELEASE.jar包，这个jar包专门用来支持JUnit基于注解的测试的，
 * 该jar包在spring-4.2.4-core中
 * 该jar包里有个SpringJUnit4ClassRunner.class，用@RunWith注解加进来即可
 * 
 * 注解@ContextConfiguration表示将ApplicationContext对象注入进来，就不用像以往那样在测试程序里先new了，直接使用
 * http://stackoverflow.com/questions/10385452/location-of-spring-context-xml
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/SpringBeans.xml")
//@ContextConfiguration(locations = "classpath:/SpringBeans.xml")
public class SSHTest {

	@Resource
	private Date date;
	@Resource  
    private CategoryService categoryService;
	
	@Test
	public void springIoc() {
		System.out.println(date);
	}
	
	@Test
	@Transactional
    public void hihernate() {  
        Category category = new Category("test", true);  
        categoryService.save(category);  
    }  
}
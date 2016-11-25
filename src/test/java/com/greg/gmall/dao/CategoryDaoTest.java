package com.greg.gmall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.junit.After;

import com.greg.gmall.model.*;

/*
 * Testing DAO or data layer is always a subject of debate. What exactly we want
 * to test? Are we just testing the methods from DAO implementation class and
 * making sure that each and every line of code in those methods is covered?
 * 
 * If we think in terms of unit-test, than our goal becomes testing every line
 * of DAO code while really mocking all the external systems/dependencies. IMO,
 * we can’t truly test a data-layer without really interacting with the database
 * itself. And then it becomes an integration test.
 * 
 * Anyway, we will perform integration-test on our DAO layer to make sure that
 * it works as expected. We will be using in-memory H2 database to do our
 * integration-tests.
 * http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-testing-example-using-annotations/ 
 * 
 * Unit testing data access components
 * http://bitbybitblog.com/unit-testing-data-access-components/
 * 
 * spring-embedded-database-examples
 * https://www.mkyong.com/spring/spring-embedded-database-examples/
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/SpringBeans-Test.xml")
@Transactional
/*
 * annotate the test class with @DirtiesContext so that the application context
 * is re-created and datasource re-initialized for each test method:
 * http://stackoverflow.com/questions/21246802/spring-junit-h2-jpa-is-it-possible-to-drop-create-the-database-for-every
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CategoryDaoTest {

	private EmbeddedDatabase db;
	@Autowired
	private CategoryDao categoryDao;

	@Before
	public void setUp() {
		db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:/com/greg/gmall/db/sql/category-scheme-test.sql")
				.addScript("classpath:/com/greg/gmall/db/sql/category-data-test.sql").build();
	}

	@Test
	public void test_get() {
		assertNotNull(categoryDao.get(1));
		assertNull(categoryDao.get(5));
	}

	@Test
	public void test_save() {
		categoryDao.save(getNewCategory());
		assertEquals(3, categoryDao.query().size());
	}

	private Category getNewCategory() {
		Category category = new Category();
		category.setId(3);
		category.setHot(false);
		category.setType("test");
		return category;
	}

	@Test
	public void test_delete_by_valid_id() {
		categoryDao.delete(1);
		assertEquals(1, categoryDao.query().size());
	}

	@Test
	public void test_delete_by_invalid_id() {
		categoryDao.delete(10);
		assertEquals(2, categoryDao.query().size());
	}

	@Test
	public void test_query() {
		assertEquals(2, categoryDao.query().size());
	}
}

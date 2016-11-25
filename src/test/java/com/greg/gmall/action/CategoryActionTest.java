package com.greg.gmall.action;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.greg.gmall.service.CategoryService;
import com.greg.gmall.model.Category;

/*
 * https://struts.apache.org/docs/struts-2-junit-plugin-tutorial.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/SpringBeans.xml")
public class CategoryActionTest {

	@Mock
	CategoryService service;

	@InjectMocks
	CategoryAction action;

	@Spy
	List<Category> categoryList = new ArrayList<Category>();

	@Spy
	ModelMap model;
	@Mock
	BindingResult result;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		categoryList = getCategoryList();
	}

	@Test
	public void test_save_category_with_success() throws Exception {
		doNothing().when(service).save(any(Category.class));
		
		assertEquals("index",action.save());
		verify(service, atLeastOnce()).save(any(Category.class));
	}

	public List<Category> getCategoryList() {
		List<Category> catgoryList = new ArrayList<Category>();
		catgoryList.add(new Category(0, "Welcome to the world!", true));
		catgoryList.add(new Category(1, "Hello there everyone...", false));
		catgoryList.add(new Category(2, "Hey there", true));
		return catgoryList;
	}
}

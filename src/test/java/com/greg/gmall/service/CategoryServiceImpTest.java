package com.greg.gmall.service;

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
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.greg.gmall.dao.BaseDao;
import com.greg.gmall.dao.CategoryDao;
import com.greg.gmall.model.Category;
import com.greg.gmall.service.imp.CategoryServiceImp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/SpringBeans.xml")
public class CategoryServiceImpTest {

	@Mock
	private CategoryDao categoryDao;
	@Mock
	private BaseDao<Category> baseDao;
	@InjectMocks
	CategoryServiceImp categoryService;
	@Spy
	List<Category> categoryList = new ArrayList<Category>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		categoryList = getCategoryList();
	}

	@Test
	public void test_save() {
		doNothing().when(baseDao).save(any(Category.class));
		categoryService.save(any(Category.class));
		verify(baseDao, atLeastOnce()).save(any(Category.class));
	}

	@Test
	public void test_get() {
		Category category = categoryList.get(0);
		when(baseDao.get(anyInt())).thenReturn(category);
		assertEquals(categoryService.get(category.getId()), category);
	}
     
    @Test
    public void test_update(){
    	Category category = categoryList.get(0);
    	when(baseDao.get(anyInt())).thenReturn(category);
        categoryService.update(category);
        verify(baseDao, atLeastOnce()).update(any(Category.class));
    }
 
    @Test
    public void test_delete(){
        doNothing().when(baseDao).delete(anyInt());
        categoryService.delete(anyInt());
        verify(baseDao, atLeastOnce()).delete(anyInt());
    }
     
    @Test
    public void test_query(){
        when(baseDao.query()).thenReturn(categoryList);
        assertSame(categoryService.query(), categoryList);
    }

	public List<Category> getCategoryList() {
		List<Category> catgoryList = new ArrayList<Category>();
		catgoryList.add(new Category(0, "Welcome to the world!", true));
		catgoryList.add(new Category(1, "Hello there everyone...", false));
		catgoryList.add(new Category(2, "Hey there", true));
		return catgoryList;
	}

}

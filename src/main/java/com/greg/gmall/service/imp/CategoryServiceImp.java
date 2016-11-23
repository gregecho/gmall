package com.greg.gmall.service.imp;

import com.greg.gmall.dao.CategoryDao;
import com.greg.gmall.model.Category;
import com.greg.gmall.service.CategoryService;

public class CategoryServiceImp implements CategoryService {
	
	CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void save(Category category) {
		categoryDao.save(category);
	}
}

package com.greg.gmall.dao.imp;

import org.springframework.stereotype.Repository;

import com.greg.gmall.dao.CategoryDao;
import com.greg.gmall.model.Category;

@Repository("categoryDao")
public class CategoryDaoImp extends BaseDaoImp<Category> implements CategoryDao {

}

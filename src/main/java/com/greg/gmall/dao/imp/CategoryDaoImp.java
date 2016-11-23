package com.greg.gmall.dao.imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.greg.gmall.dao.CategoryDao;
import com.greg.gmall.model.Category;

public class CategoryDaoImp implements CategoryDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Category category) {
		this.getSession().save(category);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}

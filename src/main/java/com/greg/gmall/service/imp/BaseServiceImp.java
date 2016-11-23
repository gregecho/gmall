/**
 * 
 */
package com.greg.gmall.service.imp;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.greg.gmall.dao.BaseDao;
import com.greg.gmall.dao.CategoryDao;
import com.greg.gmall.service.BaseService;

/**
 * @author greg.li
 *
 */
@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImp<T> implements BaseService<T> {

	private Class<T> persistentClass;

	public BaseServiceImp() {
		System.out.println("this represent object for invoke Constructor" + this);
		System.out.println("get this super class" + this.getClass().getSuperclass());
		System.out.println("get this super class including generic" + this.getClass().getGenericSuperclass());
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@PostConstruct
	public void init() {
		// Assign dao instance to baseDao according to the type of the persistentClass
		String clazzName = persistentClass.getSimpleName();
		String clazzDao = clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1) + "Dao"; // Account==>accountDao
		System.out.println(clazzDao);
		try {
			Field clazzField = this.getClass().getSuperclass().getDeclaredField(clazzDao);
			Field baseField = this.getClass().getSuperclass().getDeclaredField("baseDao");
			baseField.set(this, clazzField.get(this));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected BaseDao<T> baseDao;
	
	@Resource
	protected CategoryDao categoryDao;
	
	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}

	@Override
	public T get(int id) {
		return (T) baseDao.get(id);
	}

	@Override
	public List<T> query() {
		return baseDao.query();
	}

}

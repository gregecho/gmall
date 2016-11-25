/**
 * 
 */
package com.greg.gmall.dao.imp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.greg.gmall.dao.BaseDao;

/**
 * @author greg.li
 *
 */
@SuppressWarnings("unchecked")
@Repository("baseDao")
@Lazy(true)
public class BaseDaoImp<T> implements BaseDao<T> {

	private Class<T> persistentClass;

	public BaseDaoImp() {
		System.out.println("this represent object for invoke Constructor" + this);
		System.out.println("get this super class" + this.getClass().getSuperclass());
		System.out.println("get this super class including generic" + this.getClass().getGenericSuperclass());
		Type type = getClass().getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			type = getClass().getSuperclass().getGenericSuperclass();
		}
		this.persistentClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		System.out.println(persistentClass.getSimpleName());
		String hql = "delete " + persistentClass.getSimpleName() + " as c where c.id=:id";
		getSession().createQuery(hql).setParameter("id", id, IntegerType.INSTANCE).executeUpdate();
	}

	@Override
	public T get(int id) {
		return (T) getSession().get(persistentClass, id);
	}

	@Override
	public List<T> query() {
		String hql = "from " + persistentClass.getSimpleName();
		return getSession().createQuery(hql).getResultList();
	}

}

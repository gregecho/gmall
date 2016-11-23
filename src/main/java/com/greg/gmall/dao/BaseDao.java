/**
 * 
 */
package com.greg.gmall.dao;

import java.util.List;

/**
 * @author greg.li
 *
 */
public interface BaseDao<T> {
	public void save(T t);

	public void update(T t);

	public void delete(int id);

	public T get(int id);

	public List<T> query();
}

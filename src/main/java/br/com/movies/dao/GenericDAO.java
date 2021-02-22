package br.com.movies.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {

	/**
	 * @throws Exception.
	 */
	void create(T object) throws Exception;

	/**
	 * @throws Exception.
	 */
	void update(T object) throws Exception;

	/**
	 * @param id
	 * 
	 */
	T findById(final PK id);
	
}

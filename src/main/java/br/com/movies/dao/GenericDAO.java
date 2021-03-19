package br.com.movies.dao;

import java.io.Serializable;
import java.util.List;

import br.com.hibernate.exception.GenericDAOServiceException;

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
	
	/**
	 * 
	 * @param entityList
	 * @throws GenericDAOServiceException
	 */
	void create(List<T> entityList) throws GenericDAOServiceException;
	
	/**
	 * 
	 * @param namedParam
	 * @param valueParam
	 * @return
	 */
	T findByParam(final String namedParam, final Object valueParam);
	
}

package br.com.movies.abstrat;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.movies.service.impl.HibernateDAOServiceImpl;

public abstract class BasicEntity <T, PK extends Serializable> extends HibernateDAOServiceImpl<T, PK>{

	/**
	 * Constructor
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicEntity() {

		Class daoClass;
		if (this.getClass().getSuperclass() == BasicEntity.class) {
			daoClass = this.getClass();
		} else {
			Class cglibConcreteDaoClass = this.getClass();
			daoClass = cglibConcreteDaoClass.getSuperclass();
		}
		ParameterizedType genericDaoType = (ParameterizedType) daoClass.getGenericSuperclass();
		Type entityType = (genericDaoType).getActualTypeArguments()[0];
		persistentClass = (Class<T>) entityType;
	}

	/**
	 * @param sessionFactory
	 */
	@Autowired
	public final void setHibernateSessionFactory(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}

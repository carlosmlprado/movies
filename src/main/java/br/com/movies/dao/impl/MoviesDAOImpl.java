package br.com.movies.dao.impl;

import org.hibernate.query.Query;

import br.com.hibernate.BasicEntity;
import br.com.movies.dao.MoviesDAO;
import br.com.movies.entity.MoviesEntity;

public class MoviesDAOImpl extends BasicEntity<MoviesEntity, Integer> implements MoviesDAO {

	@Override
	public Integer veriFyGenreData() {

		StringBuilder sql = new StringBuilder();

		sql.append("Select count(id) from movie_genre limit 1");

		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(sql.toString());
		return (Integer) query.uniqueResult();
	}

}

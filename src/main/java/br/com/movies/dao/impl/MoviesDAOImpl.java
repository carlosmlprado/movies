package br.com.movies.dao.impl;

import java.math.BigInteger;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hibernate.BasicEntity;
import br.com.movies.dao.MovieDAO;
import br.com.movies.entity.MovieEntity;

@Repository("moviesDAO")
public class MoviesDAOImpl extends BasicEntity<MovieEntity, Integer> implements MovieDAO {

	@Override
	@Transactional(readOnly = true)
	public BigInteger veriFyGenreData() {

		StringBuilder sql = new StringBuilder();

		sql.append("Select count(*) from movie_genre");

		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		return (BigInteger) query.uniqueResult();
	}

}

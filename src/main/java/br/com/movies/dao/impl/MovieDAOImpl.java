package br.com.movies.dao.impl;

import java.math.BigInteger;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.movies.dao.MovieDAO;
import br.com.movies.dto.MovieDTO;
import br.com.movies.entity.BasicEntity;
import br.com.movies.entity.MovieEntity;

@Repository("movieDAO")
public class MovieDAOImpl extends BasicEntity<MovieEntity, Integer> implements MovieDAO{

//	@Override
	@Transactional(readOnly = true)
	public BigInteger veriFyGenreData() {

		StringBuilder sql = new StringBuilder();

		sql.append("Select count(*) from movie_genre");

		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		return (BigInteger) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public MovieDTO findByParam(final String namedParam) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT adult \"adult\", backdrop_path \"backDropPath\" ,");
		sql.append("  posterpath \"posterPath\", original_title \"originalTitle\", ");
		sql.append(" release_date \"releaseDate\", budget \"budget\", overview \"overview\", movie_api_id \"movieApiId\" ");
		sql.append(" FROM movies WHERE original_title = '" + namedParam + "' limit 1");
		
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		return (MovieDTO) query.uniqueResult();
		
	}

	@Override
	public MovieEntity findByOriginalTitle(String originalTitle) {
		
		
		return null;
	}

}

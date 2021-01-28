package br.com.movies.dao;

import br.com.hibernate.GenericDAO;
import br.com.movies.entity.MoviesEntity;

public interface MoviesDAO extends GenericDAO<MoviesEntity, Integer>{
	
//	verify if genres already were persisteds.
	Integer veriFyGenreData();

}

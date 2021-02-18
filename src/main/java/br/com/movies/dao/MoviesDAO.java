package br.com.movies.dao;

import java.math.BigInteger;

import br.com.movies.entity.MoviesEntity;

public interface MoviesDAO extends GenericDAO<MoviesEntity, Integer>{
	
//	verify if genres already were persisteds.
	BigInteger veriFyGenreData();

}

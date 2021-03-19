package br.com.movies.dao;

import java.math.BigInteger;

import br.com.movies.entity.MovieEntity;

public interface MovieDAO extends GenericDAO<MovieEntity, Integer>{
	
//	verify if genres already were persisteds.
	BigInteger veriFyGenreData();

}

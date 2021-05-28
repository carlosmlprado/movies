package br.com.movies.dao;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import br.com.movies.dto.MovieDTO;
import br.com.movies.entity.MovieEntity;

@Repository
public interface MovieDAO extends GenericDAO<MovieEntity, Integer> {

//	verify if genres already were persisteds.
	BigInteger veriFyGenreData();

	/**
	 * 
	 * @param namedParam
	 * @return
	 */
	MovieDTO findByParam(final String namedParam);

	MovieEntity findByOriginalTitle(String originalTitle);

}

package br.com.movies.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.hibernate.BasicEntity;
import br.com.movies.dao.MovieGenreDAO;
import br.com.movies.entity.RelMovieGenreEntity;

@Repository("movieGenreDAO")
public class MovieGenreDAOImpl extends BasicEntity<RelMovieGenreEntity, Integer> implements MovieGenreDAO{

}

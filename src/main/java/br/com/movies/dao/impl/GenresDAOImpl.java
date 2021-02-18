package br.com.movies.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.movies.dao.GenresDAO;
import br.com.movies.entity.GenreEntity;

@Repository("genresDAO")
public class GenresDAOImpl extends BasicEntity<GenreEntity, Integer> implements GenresDAO {

}

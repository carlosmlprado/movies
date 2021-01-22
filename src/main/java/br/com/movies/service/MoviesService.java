package br.com.movies.service;

import java.util.List;

import br.com.movies.dto.GenresDTO;
import br.com.movies.dto.MoviesDTO;

public interface MoviesService {

	/**
	 * Return the movie by its ID
	 * @return
	 */
	public MoviesDTO getMoviesByParameter(Integer movieID, String movieName);
	
	/**
	 * Method to save the genres and use it for anothers calls that the genre only come by id.
	 * @param list
	 */
	public void saveGenres(List<GenresDTO> list);
}

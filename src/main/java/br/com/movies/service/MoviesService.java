package br.com.movies.service;

import br.com.movies.dto.MoviesDTO;

public interface MoviesService {

	/**
	 * Return the movie by its ID
	 * @return
	 */
	public MoviesDTO getMoviesByParameter(Integer movieID, String movieName, Boolean saveAsFavorite);
	
	/**
	 * Method to get the genres and persist at the first time the application runs.
	 */
	public Boolean getGenres();
}

package br.com.movies.service;

import br.com.movies.dto.GenresDTO;
import br.com.movies.dto.MoviesDTO;
import br.com.movies.dto.ReviewDTO;
import br.com.movies.dto.UpcomingDTO;

public interface MoviesService {

	/**
	 * Return the movie by its ID
	 * @return
	 */
	public MoviesDTO getMoviesByParameter(Integer movieID, String movieName, Boolean saveAsFavorite);
	
	/**
	 * Method to get the genres and persist at the first time the application runs.
	 */
	public GenresDTO getGenres();
	
	/**
	 *  
	 * @param language (us or pt)
	 * @return10 movies upcoming
	 */
	public UpcomingDTO getUpcoming(String language);
	
	/**
	 * 
	 * @param id
	 * @return Reviews from the movie
	 */
	public ReviewDTO getReviews(Integer id, String language);
}

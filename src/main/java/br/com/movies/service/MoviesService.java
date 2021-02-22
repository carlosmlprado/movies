package br.com.movies.service;

import br.com.movies.dto.GenresDTO;
import br.com.movies.dto.MoviesDTO;
import br.com.movies.dto.RatingResponseDTO;
import br.com.movies.dto.ReviewDTO;
import br.com.movies.dto.UpcomingDTO;

public interface MoviesService {

	/**
	 * Return the movie by its ID
	 * @return
	 */
	MoviesDTO getMoviesByParameter(Integer movieID, String movieName, Boolean saveAsFavorite);
	
	/**
	 * Method to get the genres and persist at the first time the application runs.
	 */
	GenresDTO getGenres();
	
	/**
	 *  
	 * @param language (us or pt)
	 * @return10 movies upcoming
	 */
	UpcomingDTO getUpcoming(String language);
	
	/**
	 * 
	 * @param id
	 * @return Reviews from the movie
	 */
	ReviewDTO getReviews(Integer id, String language);
	
	/**
	 * 
	 * @param id
	 * @return MEssage code with success or not
	 */
	RatingResponseDTO createRate(Integer id, Double rate);
}

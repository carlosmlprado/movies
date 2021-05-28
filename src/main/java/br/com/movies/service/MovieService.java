package br.com.movies.service;

import java.util.Optional;

import br.com.movies.dto.GenresDTO;
import br.com.movies.dto.MovieDTO;
import br.com.movies.dto.MoviesFromApiImdbDTO;
import br.com.movies.dto.RatingResponseDTO;
import br.com.movies.dto.ReviewDTO;
import br.com.movies.dto.UpcomingDTO;

public interface MovieService {

	/**
	 * Return the movie by its ID
	 * 
	 * @return
	 */
	MoviesFromApiImdbDTO getMoviesByParameter(Integer movieID, String movieName);

	/**
	 * Method to get the genres and persist at the first time the application runs.
	 */
	GenresDTO getGenres();

	/**
	 * 
	 * @param language (us or pt)
	 * @return10 movies upcoming
	 */
	Optional<UpcomingDTO> getUpcoming(String language);

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

	/**
	 * 
	 * @param moviesDTO
	 * @return
	 */
	MovieDTO saveMovie(MovieDTO moviesDTO);

	/**
	 * 
	 * @param name
	 * @return
	 */
	MovieDTO getMovie(String name);

}

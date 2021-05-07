package br.com.movies.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.movies.dao.GenresDAO;
import br.com.movies.dao.MovieDAO;
import br.com.movies.dao.MovieGenreDAO;
import br.com.movies.dto.GenresDTO;
import br.com.movies.dto.MovieDTO;
import br.com.movies.dto.MoviesFromApiImdbDTO;
import br.com.movies.dto.RatingResponseDTO;
import br.com.movies.dto.ReviewDTO;
import br.com.movies.dto.UpcomingDTO;
import br.com.movies.entity.GenreEntity;
import br.com.movies.entity.MovieEntity;
import br.com.movies.entity.RelMovieGenreEntity;
import br.com.movies.service.MovieService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("moviesService")
public class MoviesServiceImpl implements MovieService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MovieDAO moviesDAO;
	@Autowired
	private GenresDAO genresDAO;
	@Autowired
	private MovieGenreDAO movieGenreDAO;

	@Value("${url.api}")
	private String url;
	@Value("${token}")
	private String token;
	@Value("${chave.api}")
	private String key;

	@Override
	@Transactional
	public MoviesFromApiImdbDTO getMoviesByParameter(Integer movieID, String movieName) {

		log.info("Building url");
		String urlFinal;
		if (0 != movieID) {
			urlFinal = url.concat("/movie/").concat(String.valueOf(movieID)).concat("?api_key=" + key);

		} else {
			urlFinal = url.concat("/search/movie?api_key=").concat(key).concat("&query=").concat(movieName);
		}

		HttpEntity<MoviesFromApiImdbDTO> response = null;

		try {
			log.info("Building headers");
			HttpHeaders headers = mountHeaders();

			response = restTemplate.exchange(urlFinal, HttpMethod.GET, new HttpEntity<>("parameters", headers),
					MoviesFromApiImdbDTO.class);

		} catch (Exception e) {
			log.error("Error calling API");
			return null;
		}
		response.getBody().getGenreIds().stream().filter(g -> g.equals("Joaquim")).forEach(System.out::print);
		return response.getBody();
	}

	private HttpHeaders mountHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.ALL));
		headers.add("Authorization", "Bearer ".concat(token));
		return headers;
	}

	@Override
	public GenresDTO getGenres() {

		log.info("Verify if genres already were saved");
		if (veriFyGenreData().equals(new BigInteger("0"))) {

			log.info("Building url");
			String urlGenres = url.concat("/genre/movie/list?api_key=").concat(key).concat("&language=en-US");

			HttpEntity<GenresDTO> response = null;
			HttpHeaders headers = mountHeaders();

			try {
				response = restTemplate.exchange(urlGenres, HttpMethod.GET, new HttpEntity<>("parameters", headers),
						GenresDTO.class);

				List<GenreEntity> genresEntity = new ArrayList<>();

				response.getBody().getGenres().stream().forEach(r -> {
					GenreEntity genreEntity = new GenreEntity();

					genreEntity.builder(r);
					genresEntity.add(genreEntity);
				});

				genresDAO.create(genresEntity);

				return response.getBody();

			} catch (Exception e) {
				log.error("Error consulting API");
				return response.getBody();

			}
		}
		return null;
	}

	@Transactional(readOnly = true)
	private BigInteger veriFyGenreData() {
		return moviesDAO.veriFyGenreData();
	}

	@Override
	public Optional<UpcomingDTO> getUpcoming(String language) {

		log.info("Building upcoming url");
		String urlUpcoming = url.concat("/movie/upcoming?api_key=").concat(key).concat("&language=").concat(language)
				.concat("&page=1");

		HttpEntity<UpcomingDTO> response = null;
		HttpHeaders headers = mountHeaders();

		response = restTemplate.exchange(urlUpcoming, HttpMethod.GET, new HttpEntity<>("parameters", headers),
				UpcomingDTO.class);

		log.debug("Upcoming Response: " + response.toString());

		return Optional.ofNullable(response.getBody());
	}

	@Override
	public ReviewDTO getReviews(Integer id, String language) {

		log.info("Building reviews url");
		String urlUpcoming = url.concat("/movie/").concat(String.valueOf(id)).concat("/reviews?api_key").concat(key)
				.concat("&language=").concat(language);

		HttpEntity<ReviewDTO> response = null;
		HttpHeaders headers = mountHeaders();

		try {
			response = restTemplate.exchange(urlUpcoming, HttpMethod.GET, new HttpEntity<>("parameters", headers),
					ReviewDTO.class);

			log.debug("Reviews Response: " + response.toString());

			return response.getBody();

		} catch (Exception e) {
			log.error("Error calling API Reviews: " + e.getMessage());
			return null;
		}

	}

	@Override
	public RatingResponseDTO createRate(Integer id, Double rate) {

		log.info("Building rating url");

		String urlRating = url.concat("/movie/").concat(String.valueOf(id)).concat("/rating?api_key=").concat(key);

		RatingResponseDTO rating = new RatingResponseDTO();
		rating.setValue(String.valueOf(rate));

		HttpHeaders headers = mountHeaders();
		HttpEntity<RatingResponseDTO> response = new HttpEntity<>(rating, headers);

		try {

			restTemplate.postForObject(urlRating, response, RatingResponseDTO.class);

			response = restTemplate.exchange(urlRating, HttpMethod.POST, new HttpEntity<>("parameters", headers),
					RatingResponseDTO.class);

			log.debug("Reviews Response: " + response.toString());

			return response.getBody();

		} catch (Exception e) {
			log.error("Error calling API Reviews: " + e.getMessage());
			return null;
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MovieDTO saveMovie(MovieDTO movieDTO) {
		log.info("Saving the movie");

		MovieEntity moviesEntity = new MovieEntity();

		try {
			log.info("Checking if the movie is already persisted");

			moviesEntity = moviesDAO.findByParam("movieApiId", movieDTO.getMovieApiId());

			if (null == moviesEntity) {
				log.info("Buildilng object moviesEntity");
				moviesEntity = MovieEntity.builder(movieDTO);
				log.debug("Object moviesEntity: " + moviesEntity);

				moviesDAO.create(moviesEntity);
				movieDTO.setId(moviesEntity.getId());

				log.info("Buildilng object movieGenreEntity");

				movieDTO.getGenreIds().stream().forEach(g -> {
					RelMovieGenreEntity movieGenreEntity = new RelMovieGenreEntity();
					movieGenreEntity.setMovieId(movieDTO.getId());
					movieGenreEntity.setGenreId(g);

					try {
						movieGenreDAO.create(movieGenreEntity);

					} catch (Exception e) {
						log.error("Error creating relationship between movie and genres: " + e.getMessage());
					}
				});
			} else {
				movieDTO.setIdentifier(moviesEntity.getId());
			}

			return movieDTO;

		} catch (Exception e) {
			log.error("Error saving the movie: " + e.getMessage());

			return new MovieDTO();
		}

	}

}

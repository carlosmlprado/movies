package br.com.movies.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.movies.dao.GenresDAO;
import br.com.movies.dao.MoviesDAO;
import br.com.movies.dto.GenresDTO;
import br.com.movies.dto.MoviesDTO;
import br.com.movies.entity.GenreEntity;
import br.com.movies.entity.MoviesEntity;
import br.com.movies.service.MoviesService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("moviesService")
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private RestTemplate restTemplate;

	public MoviesServiceImpl(MoviesDAO moviesDAO, GenresDAO genresDAO) {
		super();
		this.moviesDAO = moviesDAO;
		this.genresDAO = genresDAO;
	}

	private MoviesDAO moviesDAO;
	private GenresDAO genresDAO;

	@Value("${url.api}")
	private String url;
	@Value("${token}")
	private String token;
	@Value("${chave.api}")
	private String key;

	@Override
	@Transactional
	public MoviesDTO getMoviesByParameter(Integer movieID, String movieName, Boolean saveAsFavorite) {

		log.info("Building url");
		String urlFinal;
		if (0 != movieID) {
			urlFinal = url.concat("/movie/").concat(String.valueOf(movieID)).concat("?api_key=" + key);

		} else {
			urlFinal = url.concat("/search/movie?api_key=").concat(key).concat("&query=").concat(movieName);
		}

		HttpEntity<MoviesDTO> response = null;

		try {
			log.info("Building headers");
			HttpHeaders headers = mountHeaders();

			response = restTemplate.exchange(urlFinal, HttpMethod.GET, new HttpEntity<>("parameters", headers),
					MoviesDTO.class);

			if (saveAsFavorite) {
				log.info("Getting genre to persist");
				GenreEntity genre = new GenreEntity();
				genre = genresDAO.findById(response.getBody().getGenres().get(0).getId());

				log.info("Building entity to persist");
				MoviesEntity movie = new MoviesEntity();
				movie.builder(response.getBody(), genre);

				moviesDAO.create(movie);
			}
		} catch (Exception e) {
			log.error("Error calling API");
			return null;
		}
		return response.getBody();

	}

	private HttpHeaders mountHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
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

}

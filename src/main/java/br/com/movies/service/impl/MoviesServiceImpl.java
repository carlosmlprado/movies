package br.com.movies.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.movies.dto.MoviesDTO;
import br.com.movies.service.MoviesService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("moviesService")
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${url.api}")
	private String url;
	@Value("${token}")
	private String token;
	@Value("${chave.api}")
	private String key;

	@Override
	public MoviesDTO getMoviesByParameter(Integer movieID, String movieName) {

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

}

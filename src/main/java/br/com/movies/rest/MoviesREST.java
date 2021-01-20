package br.com.movies.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.movies.dto.MoviesDTO;
import br.com.movies.service.MoviesService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/movies")
public class MoviesREST {

	private MoviesService moviesService;

	@GetMapping("/")
	public String test() {
		return "It works!";
	}

	@GetMapping("/getMovies/{movieId}/{movieName}")
	public ResponseEntity<?> getMovies(@PathVariable(value = "movieId") Integer movieId,
			@PathVariable(value = "movieName") String movieName) {

		MoviesDTO response = moviesService.getMoviesByParameter(movieId, movieName);

		if (null != response) {
			return new ResponseEntity<>(new Gson().toJson(response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Gson().toJson(response),
					HttpStatus.NOT_FOUND);
		}

	}

}

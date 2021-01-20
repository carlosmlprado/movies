package br.com.movies.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movies.dto.MoviesDTO;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/movies")
public class MoviesREST {
	
	@GetMapping("/")
	public String test() {
		return "It works!";
	}
	
	@GetMapping("/getMovies/{movieId}")
		public ResponseEntity<MoviesDTO> getMovies(@PathVariable(value="movieId") Integer movieId) {
		
		return null;
	}
		
}

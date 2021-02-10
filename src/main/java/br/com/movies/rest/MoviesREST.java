package br.com.movies.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.movies.dto.GenresDTO;
import br.com.movies.dto.MoviesDTO;
import br.com.movies.dto.RatingResponseDTO;
import br.com.movies.dto.ReviewDTO;
import br.com.movies.dto.UpcomingDTO;
import br.com.movies.feign.GetLoginFeign;
import br.com.movies.service.MoviesService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/movies")
public class MoviesREST {

//	Not using Autowired to avoid memory leak
	private MoviesService moviesService;
	private GetLoginFeign login;

	@GetMapping("/")
	public String test() {
		return "It works!";
	}

	@GetMapping("/getMovies/{movieId}/{movieName}/{saveAsFavorite}")
	public ResponseEntity<?> getMovies(@PathVariable(value = "movieId") Integer movieId,
			@PathVariable(value = "movieName") String movieName,
			@PathVariable(value = "saveAsFavorite") Boolean saveAsFavorite) {

		MoviesDTO response = moviesService.getMoviesByParameter(movieId, movieName, saveAsFavorite);

		if (null != response) {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getGenres")
	public ResponseEntity<?> getGenres() {

		GenresDTO response = moviesService.getGenres();
		return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);

	}

	@GetMapping("/getUpcoming/{language}")
	public ResponseEntity<?> getUpcoming(@PathVariable(value = "language") String language) {

		UpcomingDTO response = moviesService.getUpcoming(language);

		if (null != response) {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getReviews/{id}/{language}")
	public ResponseEntity<?> getReviews(@PathVariable(name = "id") Integer id,
			@PathVariable(value = "language") String language) {

		ReviewDTO response = moviesService.getReviews(id, language);

		if (null != response) {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/createRate/{movieId}/{rate}")
	public ResponseEntity<?> createRate(@PathVariable(name = "movieId") Integer movieId,
			@PathVariable(name = "rate") Double rate){

		RatingResponseDTO response = moviesService.createRate(movieId, rate);

		if (null != response) {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getLogin/{cpf}/{name}")
	public ResponseEntity<?> getLogin(@PathVariable("cpf") String cpf, @PathVariable("name") String name){
		
		return new ResponseEntity<>(new Gson().toJson(login.getLogin(cpf, name)), HttpStatus.OK);
	}

}

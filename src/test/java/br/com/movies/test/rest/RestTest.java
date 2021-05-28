package br.com.movies.test.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.movies.entity.MovieEntity;
import br.com.movies.rest.MoviesREST;
import br.com.movies.service.MovieService;

//Using RANDOM_PORT to not conflict the ports when testing
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//AutoConfigureMockMvc to inject mockMvc
@AutoConfigureMockMvc
//We can narrow the tests to only the web layer with @WebMvcTest. Spring Boot instantiates only the web layer rather than the whole context
@WebMvcTest(MoviesREST.class)
@RunWith(SpringRunner.class)
public class RestTest {

//	@LocalServerPort
//	private int port;

//	Using MockMvc to not start the server to test It. 
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	MovieService movieService;

	@Test
	public void testShoudReturnItWorks() throws Exception {
		mockMvc.perform(get("/movies/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("It works!"));
	}

	@Test
	public void getMovies() {
		String endpoint = "getMovies/268/batman";
		
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setOriginalTitle("Harry Potter");
		
		
	}
}

package br.com.movies.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.movies.MoviesApplicationTests;
import br.com.movies.rest.MoviesREST;

public class MoviesRestTest extends MoviesApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private MoviesREST moviesRest;

	@Before
	public void setUp() {

		this.mockMvc = MockMvcBuilders.standaloneSetup(moviesRest).build();
	}

	@Test
	public void testGetTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/movies")).andExpect(MockMvcResultMatchers.status().isOk());
	}

}

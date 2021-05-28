package br.com.movies.test.persistence;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.movies.dao.MovieDAO;
import br.com.movies.entity.MovieEntity;

//RunWith(SpringRunner.class) provides a bridge between Spring Boot test features and JUnit
@RunWith(SpringRunner.class)
//DataJpaTest provides some standard setup needed for testing the persistence layer:
@DataJpaTest
@TestPropertySource(
		  locations = "classpath:application-test.properties")
public class PersistenceMovieTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@MockBean
	MovieDAO movieDAO;
	
	@Before
	public void setUp() {
		MovieEntity movie = new MovieEntity();
		movie.setId(1);
		movie.setOriginalTitle("Harry Potter");

	    Mockito.when(movieDAO.findByOriginalTitle(movie.getOriginalTitle()))
	      .thenReturn(movie);
	}
	
	@Test
	public void shouldReturnMovieCreated() {

		MovieEntity movie = new MovieEntity();

		movie.setOriginalTitle("Harry Potter");
		testEntityManager.persist(movie);
		testEntityManager.flush();
		
		MovieEntity movieEntityFound = movieDAO.findByOriginalTitle(movie.getOriginalTitle());
		
		assertThat(movieEntityFound.getOriginalTitle()).isEqualTo(movie.getOriginalTitle());
		
	}
}

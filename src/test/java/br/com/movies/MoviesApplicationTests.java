package br.com.movies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class MoviesApplicationTests {
	

	@Test
	public void contextLoads() {
		
	}

}

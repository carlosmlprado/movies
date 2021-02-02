//package br.com.movies;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.movies.dto.MoviesDTO;
//import br.com.movies.service.MoviesService;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class MoviesApplicationTests {
//	
//	@Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MoviesService service;
//
//	@Test
//	void case1() throws JsonProcessingException, Exception {
//		
//		MoviesDTO movies = new MoviesDTO();
//				
//		mockMvc.perform(post("/movies")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(movies)))
//                .andExpect(status().isOk());
//	}
//
//}

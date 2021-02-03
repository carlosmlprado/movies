package br.com.movies.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UpcomingDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<MoviesDTO> results;

}

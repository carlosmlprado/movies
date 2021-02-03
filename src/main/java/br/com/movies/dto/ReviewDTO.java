package br.com.movies.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReviewDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Integer movieId;
	
	List<ReviewResultsDTO> results;
	
}

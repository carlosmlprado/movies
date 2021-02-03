package br.com.movies.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReviewResultsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String author;
	private String content;
	
	@JsonProperty("author_details")
	private ReviewResultsAuthorDetailsDTO authorDetails;
}

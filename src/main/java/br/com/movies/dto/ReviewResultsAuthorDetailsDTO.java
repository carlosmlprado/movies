package br.com.movies.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReviewResultsAuthorDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@JsonProperty("username")
	private String userName;

	@JsonProperty("avatar_path")
	private String avatarPath;
	
	private Integer rating;
}

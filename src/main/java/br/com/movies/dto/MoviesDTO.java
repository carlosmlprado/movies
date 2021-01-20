package br.com.movies.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MoviesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("adult")
	private Boolean isAbove18;
	
	@JsonProperty("backdrop_path")
	private String backDropPath;
	
	private String budget;
	
	@JsonProperty("homepage")
	private String homePage;
	
	@JsonProperty("original_title")
	private String originalTitle;
	
	List<GenresDTO> genres;
	
	private String overview;
	
	@JsonProperty("release_date")
	private String releaseDate;
}

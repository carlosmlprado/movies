package br.com.movies.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MoviesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

//	Search by id
	@JsonProperty("adult")
	private Boolean isAbove18;
	
	@JsonProperty("backdrop_path")
	private String backDropPath;

	@JsonProperty("homepage")
	private String homePage;

	@JsonProperty("release_date")
	private String releaseDate;

	@JsonProperty("original_title")
	private String originalTitle;

	private Integer budget;
	
	List<GenresAttributesDTO> genres;
	
	private String overview;
	
//	Search by name
	private Integer page;
	List<ResultsDTO> results;
	
	@JsonProperty("id")
	private Integer movieApiId;

}

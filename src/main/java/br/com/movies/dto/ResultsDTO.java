package br.com.movies.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResultsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean adult;

	@JsonProperty("backdrop_path")
	private String backDropPath;

	@JsonProperty("genre_ids")
	List<Integer> genreIds;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("original_title")
	private String originalTitle;
	
	private Integer id;
	private String title;
	private String overview;

}

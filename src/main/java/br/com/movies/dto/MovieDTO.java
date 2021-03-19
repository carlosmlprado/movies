package br.com.movies.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MovieDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer identifier;
	private Integer id;
	private Boolean adult;
	private String backDropPath;
	List<Integer> genreIds;
	private String posterPath;
	private String originalTitle;
	private String releaseDate;
	private Double budget;
	private String overview;
	private Integer movieApiId;

}

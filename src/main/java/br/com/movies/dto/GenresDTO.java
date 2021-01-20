package br.com.movies.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GenresDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Integer age;

	@JsonProperty("name")
	private String genre;
}

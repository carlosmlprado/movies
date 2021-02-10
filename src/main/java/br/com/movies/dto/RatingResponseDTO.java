package br.com.movies.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RatingResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("status_message")
	private String statusMessage;

	private String success;

	@JsonProperty("status_code")
	private Integer statusCode;
	
	private String value;

}

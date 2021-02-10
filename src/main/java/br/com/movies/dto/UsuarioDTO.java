package br.com.movies.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String cpf;
	private String name;
	
}

package br.com.movies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.movies.dto.GenresDTO;
import lombok.Data;

@Data
@Entity
@Table(name = "movie_genre")
public class GenreEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	public Integer id;
	
	@Column(name = "genre")
	private String genre;
	
	public GenreEntity builder(GenresDTO genre) {
		this.setGenre(genre.getGenre());
		return this;
	}

}

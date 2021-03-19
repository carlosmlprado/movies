package br.com.movies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rel_movie_genre")
public class RelMovieGenreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_genre_id")
	public Integer id;

	@Column(name = "genre_id")
	private Integer genreId;

	@Column(name = "movie_id")
	private Integer movieId;

}

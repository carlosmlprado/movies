package br.com.movies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.movies.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	public Integer id;

	@Column(name = "adult")
	private Boolean isAbove18;

	@Column(name = "backdrop_path")
	private String backDropPath;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "original_title")
	private String originalTitle;

	@Column(name = "budget")
	private Integer budget;

	@Column(name = "overview")
	private String overview;

	@Column(name = "posterPath")
	private String posterPath;

	@Column(name = "movie_api_id")
	private Integer movieApiId;

	public static MovieEntity builder(MovieDTO movie) {

		MovieEntity movieEntity = new MovieEntity();

		movieEntity.setIsAbove18(movie.getAdult());
		movieEntity.setBackDropPath(movie.getBackDropPath());
		movieEntity.setReleaseDate(movie.getReleaseDate());
		movieEntity.setOriginalTitle(movie.getOriginalTitle());
		movieEntity.setOverview(movie.getOverview());
		movieEntity.setMovieApiId(movie.getMovieApiId());
		movieEntity.setPosterPath(movie.getPosterPath());

		return movieEntity;
	}

}

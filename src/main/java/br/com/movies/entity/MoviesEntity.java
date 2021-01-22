package br.com.movies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.movies.dto.MoviesDTO;
import lombok.Data;

@Entity
@Table(name = "movies")
@Data
public class MoviesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	public Integer id;

	@Column(name = "adult")
	private Boolean isAbove18;

	@Column(name = "backdrop_path")
	private String backDropPath;

	@Column(name = "homepage")
	private String homePage;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "original_title")
	private String originalTitle;

	@Column(name = "budget")
	private Integer budget;

	@Column(name = "overview")
	private String overview;

	@ManyToOne
	@JoinColumn(name = "genre_id")
	private GenreEntity genre;
	
	public MoviesEntity builder(MoviesDTO movies, GenreEntity genre) {
		
		this.setIsAbove18(movies.getIsAbove18());
		this.setBackDropPath(movies.getBackDropPath());
		this.setHomePage(movies.getHomePage());
		this.setReleaseDate(movies.getReleaseDate());
		this.setOriginalTitle(movies.getOriginalTitle());
		this.setBudget(movies.getBudget());
		this.setOverview(movies.getOverview());
		this.setGenre(genre);
		
		return this;
	}

}

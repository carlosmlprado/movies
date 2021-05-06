package br.com.movies.config.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import br.com.movies.dto.MovieDTO;

@Component
@Scope("singleton")
public class MessageReceiver {

	private static final List<MovieDTO> movies = new ArrayList<>();

	public void receive(String message) {

		System.out.println(String.format("Message received: %s", message));

		if (!Strings.isNullOrEmpty(message)) {
			MovieDTO movie = new MovieDTO();
			movie.setOriginalTitle(message);
			movies.add(movie);
		}
	}

	public List<MovieDTO> getMovies() {
		return new ArrayList<MovieDTO>(movies);
	}
}

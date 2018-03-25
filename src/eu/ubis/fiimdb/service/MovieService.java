package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.db.entity.GenreEntity;
import eu.ubis.fiimdb.db.entity.MovieEntity;
import eu.ubis.fiimdb.db.repository.MovieRepository;
import eu.ubis.fiimdb.db.repository.RepositoryFactory;
import eu.ubis.fiimdb.model.Movie;

public class MovieService {

	public List<Movie> getMovies() {
		MovieRepository movieRepository = RepositoryFactory.getMovieRepository();
		List<MovieEntity> movieEntities = movieRepository.getAllMovies();

		List<Movie> movies = new ArrayList<Movie>();
		for (MovieEntity movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			movies.add(movie);
		}
		return movies;
	}

	private Movie mapMovieEntityToModel(MovieEntity movieEntity) {
		Movie movie = new Movie();

		movie.setId(movieEntity.getId());
		movie.setReleaseDate(movieEntity.getReleaseDate());
		movie.setName(movieEntity.getName());
		movie.setRating(movieEntity.getRating());
		movie.setLength(movieEntity.getLength());
		movie.setCasting(movieEntity.getCasting());
		movie.setDirector(movieEntity.getDirector());
		movie.setDescription(movieEntity.getDescription());
		movie.setWriter(movieEntity.getWriter());
		/*
		 * TO DO:
		 * set the list of genres for the movie
		 */
		
		return movie;
	}


	/*
	 * Declare the search method
	 * public List<Movie> search(String criteria, String value) {}
	 */
	


}

package eu.ubis.fiimdb.controller;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.db.entity.MovieEntity;
import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieBean {
	private MovieService movieService = ServiceFactory.getMovieService();
	private List<Movie> movies = new ArrayList<Movie>();
	
	public void getAllMovies() {
		movies = movieService.getMovies();
		
	}
	
	 /* 
	  * TO DO: 
	  *  declare the search method
	  */

	public List<Movie> getMovies() {
		return movies;
	}
	
	public List<MovieEntity> search(String criteria, String value)
	{
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		return movies;
	}
	
	
	
}

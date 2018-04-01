package eu.ubis.fiimdb.controller;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.dao.MovieDao;
import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieBean {
	private MovieService movieService = ServiceFactory.getMovieService();
	private List<Movie> movies = new ArrayList<Movie>();
	private List<Movie> searchedMovies = new ArrayList<Movie>();
	
	public void getAllMovies() {
		movies = movieService.getMovies();
		
	}
	

	public List<Movie> getMovies() {
		return movies;
	}
	
	public List<Movie> search(String criteria, String value)
	{
		searchedMovies = movieService.search(criteria,value);
		return searchedMovies;
	}
	
	
	
}

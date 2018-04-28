package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import eu.ubis.fiimdb.dao.MovieDao;
import eu.ubis.fiimdb.dao.GenreDao;
import eu.ubis.fiimdb.model.Movie;

public class MovieService {

	private EntityManager entityManager;

	public MovieService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}

	public List<Movie> getMovies() {
		//EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		//entityManager = emFactory.createEntityManager();
		List<MovieDao> movieEntities = entityManager.createNamedQuery("getAllMovies", MovieDao.class).getResultList();

		List<Movie> movies = new ArrayList<Movie>();
		for (MovieDao movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			movies.add(movie);
		}
		return movies;
	}

	private Movie mapMovieEntityToModel(MovieDao movieEntity) {
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
		StringBuilder movieGenre = new StringBuilder();
		for (GenreDao genre : movieEntity.getGenres()) {
			movieGenre.append(genre.getType());
			movieGenre.append(" ");
		}
		movie.setGenre(movieGenre.toString());

		return movie;
	}

	/*
	 * Declare the search method public List<Movie> search(String criteria,
	 * String value) {}
	 */
	public List<Movie> search(String criteria, String value) {
		List<MovieDao> movieEntities;
		List<Movie> movies = new ArrayList<Movie>();
		if (criteria.equals("name")) {
			TypedQuery<MovieDao> search = entityManager.createNamedQuery("searchByName", MovieDao.class);
			search.setParameter(1, "%" + value + "%");
			movieEntities = search.getResultList();
		} else if (criteria.equals("year")) {
			TypedQuery<MovieDao> search = entityManager.createNamedQuery("searchByYear", MovieDao.class);
			search.setParameter(1, Integer.parseInt(value));
			movieEntities = search.getResultList();
		} else if (criteria.equals("genre")) {
			List<Movie> searchedMovies = new ArrayList<Movie>();
			movies = this.getMovies();
			for (Movie movie : movies)
				if (movie.getGenre().contains(value))
					searchedMovies.add(movie);
			return searchedMovies;
		} else
			movieEntities = new ArrayList<MovieDao>();

		for (MovieDao movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			movies.add(movie);
		}
		return movies;
	}

	public Movie getSingleMovie(String id) {
		int movieId = Integer.parseInt(id);
		TypedQuery<MovieDao> search = entityManager.createNamedQuery("getById", MovieDao.class);
		search.setParameter(1, movieId);
		MovieDao movieEntity = search.getSingleResult();
		return mapMovieEntityToModel(movieEntity);

	}

	public boolean updateMovie(Movie movie, int id) {
		boolean ok=true;
		MovieDao movieDao = new MovieDao();
		try {
			entityManager.getTransaction().begin();
			movieDao = entityManager.find(MovieDao.class, id);

			movieDao.setName(movie.getName());
			movieDao.setCasting(movie.getCasting());
			movieDao.setDescription(movie.getDescription());
			movieDao.setLength(movie.getLength());
			movieDao.setRating(movie.getRating());
			movieDao.setWriter(movie.getWriter());
			movieDao.setDirector(movie.getDirector());
			/*
			 * List<GenreDao> movieGenres = new ArrayList<>(); for(int
			 * movieGenreId : movieGenreIds) { GenreDao movieGenre = new
			 * GenreDao(); movieGenre.setId(movieGenreId);
			 * movieGenres.add(movieGenre); } movieDao.setGenres(movieGenres);
			 */

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			ok = false;
			System.out.println(e.getMessage());
		} finally {
			entityManager.close();
	
		}
       return ok;
	}

}

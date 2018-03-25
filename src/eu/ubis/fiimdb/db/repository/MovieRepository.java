package eu.ubis.fiimdb.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.db.entity.GenreEntity;
import eu.ubis.fiimdb.db.entity.MovieEntity;

public class MovieRepository {
	/*
	 * TO DO:
	 *  modify the query in order to retrieve the genres of the movies 
	 */
	private static final String GET_ALL_MOVIES_SQL = "SELECT * FROM fiimdb.movie \n" ;
    private String GET_GENRES_SQL ="SELECT g.id as id, type as type FROM movie m JOIN movie_genre mg ON m.id=mg.id_movie JOIN genre g ON mg.id_genre=g.id WHERE m.id= ?";
	
	public List<MovieEntity> getAllMovies() {
		Connection con = ConnectionHelper.getConnection();
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		PreparedStatement  psGenres;
		String genreType;
		int genreId;
		ResultSet genresSet;
		try {
			ResultSet resultSet = con.createStatement().executeQuery(GET_ALL_MOVIES_SQL);
			while (resultSet.next()) {
				MovieEntity movie = new MovieEntity();
				
				movie.setId(resultSet.getInt("id"));
				movie.setReleaseDate(resultSet.getDate("release_date"));
				movie.setName(resultSet.getString("name"));
				movie.setRating(resultSet.getDouble("rating"));
				movie.setLength(resultSet.getInt("length"));
				movie.setCasting(resultSet.getString("casting"));
				movie.setDirector(resultSet.getString("director"));
				if (resultSet.getString("description") != null) {
					movie.setDescription(resultSet.getString("description"));
				} else {
 					movie.setDescription("");
				}
				
				movie.setWriter(resultSet.getString("writer"));
				
				psGenres = con.prepareStatement(GET_GENRES_SQL);
				psGenres.setInt(1,movie.getId());
				genresSet = psGenres.executeQuery();
				List<GenreEntity> genres = new ArrayList<GenreEntity>();
				while (genresSet.next())
				{
					genreId=genresSet.getInt("id");
					genreType=genresSet.getString("type");
					GenreEntity queriedGenre = new GenreEntity(genreId,genreType);
					genres.add(queriedGenre);
				}
				movie.setGenres(genres);
				
				/*
				 * add the genre of the movie 
				 * don't forget to:
				 * - create GenreEntity class
				 * - add in MovieEntity a list of GenreEntity (generate getter and setter)
				 * - override equals and hashcode for GenreEntity and MovieEntity
				 * - add to Movie model a variable genre of type String
				 */
				
				movies.add(movie);
		
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}

	/*
	 * add search method
	 */
	
	
}

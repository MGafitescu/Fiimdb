package eu.ubis.fiimdb.db.entity;

import java.util.Date;
import java.util.*;

public class MovieEntity {
	private int id;
	private Date releaseDate;
	private String name;
	private double rating;
	private int length;
	private String casting;
	private String director;
	private String description;
	private String writer;
	private List<GenreEntity> genres;
	/*
	 * TO DO:
	 * 1. add a list of GenreEntity (hint: private List<GenreEntity> genres;)
	 * 2. generate getter and setter
	 * 4. override the default constructor to initialize the list
	 * 4. override equals and hashcode
	 */
	
	public MovieEntity()
	{
		genres =  new ArrayList<GenreEntity>();
	}

	public List<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreEntity> genres) {
		this.genres = genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCasting() {
		return casting;
	}

	public void setCasting(String casting) {
		this.casting = casting;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieEntity other = (MovieEntity) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}

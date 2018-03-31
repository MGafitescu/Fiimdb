package eu.ubis.fiimdb.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(schema = "fiimdb", name = "movie")
@NamedQuery(name="getAllMovies", query="SELECT m FROM MovieDao m")
public class MovieDao {
	@Id
	@Column
	@SequenceGenerator(name="movie_seq", schema="fiimdb", sequenceName="movie_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
	private int id;
	
	@Column(name="RELEASE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseDate;
	
	private String name;
	
	private double rating;
	private int length;
	private String casting;
	private String director;
	private String description;
	private String writer;
	/*
	 * TO DO:
	 * 1. add a list of GenreEntity (hint: private List<GenreEntity> genres;)
	 * 2. generate getter and setter
	 * 4. override the default constructor to initialize the list
	 * 4. override equals and hashcode
	 */
	
	@ManyToMany
	@JoinTable(name = "movie_genre", 
		joinColumns = @JoinColumn(name = "ID_MOVIE", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "ID_GENRE", referencedColumnName = "ID"))
	private List<GenreDao> genres;
	
	

	public List<GenreDao> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDao> genres) {
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
}

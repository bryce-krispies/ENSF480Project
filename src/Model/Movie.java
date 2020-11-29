package Model;

import java.util.ArrayList;

public class Movie {
	private String name;
	private String releaseDate;
	private String genre;
	private String synopsis;
	private ArrayList<Showtime> showtimes;

	//create a new movie without any set values.
	public Movie() {
		setName(null);
		setReleaseDate(null);
		setGenre(null);
		setSynopsis(null);
		setShowtimes(null);
	}

	//create a new movie from input values;
	public Movie(String name, String releaseDate, String genre, String synposis, ArrayList<Showtime> showtimes) {
		setName(name);
		setReleaseDate(releaseDate);
		setGenre(genre);
		setSynopsis(synposis);
		setShowtimes(showtimes);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public ArrayList<Showtime> getShowtimes() {
		return showtimes;
	}

	public void setShowtimes(ArrayList<Showtime> showtimes) {
		this.showtimes = showtimes;
	}

}
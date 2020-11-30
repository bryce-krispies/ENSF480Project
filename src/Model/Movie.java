package Model;

import java.util.ArrayList;

public class Movie {
	public String name;
	public String releaseDate;
	public String genre;
	public String synopsis;
	public ArrayList<Showtime> showtimes;
	
	public void initMovie(String name, String releaseDate, String genre, String synopsis, String[] showtimes, String[] seats){
		this.name = name;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.synopsis = synopsis;
		this.showtimes = new ArrayList<Showtime>();
		for(int i = 0; i < showtimes.length; i++) {
			this.showtimes.add(new Showtime(showtimes[i], seats));
		}
	}
}
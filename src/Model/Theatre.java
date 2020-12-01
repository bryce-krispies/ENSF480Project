package Model;

import java.util.ArrayList;

public class Theatre {
    private String name;
    private ArrayList<Movie> movies;
    private Account account;


    public Theatre(String name, double balance) {
        account = new Account(balance);
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }


    // The logic for a reg user vs ordinary user
    public ArrayList<Movie> getMovies() {
        // if (!isRegisterdUser) {
        // ArrayList<Movie> ordMovie = new ArrayList<Movie>();
        // for (Movie movie : movies) {
        // if ((LocalDateTime.now().isBefore(movie.getReleaseDate()))) {
        // ordMovie.add(movie);
        // }
        // }
        // return ordMovie;
        // }
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }
    
    public Movie getMovie(String movieName) {
        for (Movie m : movies) {
        	if(m.getName().equals(movieName)) {
        		return m;
        	}
        }
        return null;
    }
}
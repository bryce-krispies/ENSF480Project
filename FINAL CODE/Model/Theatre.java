package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Theatre implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7213051544397598231L;
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

    public ArrayList<Movie> getMovies() {
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
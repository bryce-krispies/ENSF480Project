import java.util.ArrayList;

public class Theatre {
    private String name;
    private ArrayList<Movie> movies;
    private Account account;

    public Theatre(String name) {
        account = new Account();
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

}
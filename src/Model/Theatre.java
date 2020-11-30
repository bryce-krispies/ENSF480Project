import java.util.ArrayList;

public class Theatre {
    private String name;
    private String address;
    private ArrayList<Movie> movies;
    private Account account;

    public Theatre(String name, String address) {
        account = new Account();
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

}
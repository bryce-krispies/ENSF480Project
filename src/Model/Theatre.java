package Model;

import java.util.ArrayList;

public class Theatre {
	private String name;
	private String address;
	private ArrayList<Movie> movies;
	private Account account;

	public Theatre() {
		this.setName(null);
		this.setAddress(null);
		this.setMovies(null);
		this.setAccount(null);
	}

	public Theatre(String name, String address, ArrayList<Movie> movies, Account account) {
		this.setName(name);
		this.setAddress(address);
		this.setMovies(movies);
		this.setAccount(account);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
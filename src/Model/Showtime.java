package Model;

import java.util.ArrayList;

public class Showtime {
	private Movie movie;
	private ArrayList<Seat> seats;
	private String time;
	private boolean isFull;

	public Showtime() {
		setMovie(null);
		setSeats(null);
		setTime(null);
		setFull(false);
	}

	public Showtime(Movie movie, ArrayList<Seat> seats, String time, boolean isFull) {
		setMovie(movie);
		setSeats(seats);
		setTime(time);
		setFull(isFull);
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
}
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.RegisteredUser;
import Model.Seat;
import Model.Showtime;
import Model.Theatre;
import Model.Ticket;
import Model.User;
import Model.Movie;

public class DBManager {
	// a block of constant fixed strings, these are the files that we will use for
	// the db simulation.
	final String RUsDB = "RegisteredUsers.db";
	final String theatresDB = "Theatres.db";
	final String moviesDB = "Movies.db";
	final String showtimesDB = "Showtimes.db";
	final String seatsDB = "Seats.db";

	public DBManager(Theatre theatre) {
		ArrayList<Movie> tempMovies = new ArrayList<Movie>(); // lets add some fake movies
		tempMovies.add(new Movie("Batman", LocalDateTime.of(2020, 11, 29, 22, 25), "Comedy", "A bat struggles to act human"));
		tempMovies.add(new Movie("Manbat", LocalDateTime.of(2020, 12, 5, 11, 35), "Documentary", "A human struggles to act like a bat"));
		tempMovies.add(new Movie("Scream", LocalDateTime.of(2020, 11, 30, 1, 50), "Sci-Fi", "AAAAAAAAAAAAAAAAAH!"));

		int j = 0;
		for (Movie m : tempMovies) {
			ArrayList<Showtime> theseShowtimes = new ArrayList<Showtime>(); // lets add some fake showtimes
			theseShowtimes.add(new Showtime(LocalDateTime.now().plusHours((long)(Math.random() * (12 - 2 + 1) + 2)), 9)); //TODO Fix timing logic (what if number occurs twice?)
			// and then lets init the seats for these showtimes.
			
			for (Showtime s : theseShowtimes) {
				ArrayList<Ticket> tickets = new ArrayList<Ticket>(9);
				for(int i = 0; i < 9; i++) {
					Ticket ticket = new Ticket(Integer.toString(j++), 12.00);
					tickets.add(ticket);
				}
				s.setTickets(tickets);
				s.setSeats();
			}
			m.setShowtime(theseShowtimes);
		}

		theatre.setMovies(tempMovies);
	}

	DBManager() {
		boolean testing = true;
		// if using sql, connect to db here.
		// but we are just using serialized files.
		// so lets go ahead and create some dummy files, so long as they do not exist.
		if (testing) {
			ArrayList<RegisteredUser> tempRegisteredUsers = new ArrayList<RegisteredUser>(); // lets add some good fake
			// users to this file
			tempRegisteredUsers.add(new RegisteredUser("Richard Kickem", "outtaGum", null, null, null, "101 URB St"));
			tempRegisteredUsers.add(new RegisteredUser("Ernest Dude", "password", null, null, null, "444 Cyprus St"));
			tempRegisteredUsers.add(new RegisteredUser("Mann Hecker", "p@5sw0rd", null, null, null, "Earth"));
			tempRegisteredUsers.add(new RegisteredUser("Robert'); DROP TABLE Students;--", "xkcd327!", null, null, null,
					"118 1 Ave NE"));
			ArrayList<Theatre> tempTheatres = new ArrayList<Theatre>(); // lets add some fake theatres
			tempTheatres.add(new Theatre("Chinook", 500));
//			tempTheatres.add(new Theatre("West Edmonton Mall", 200));
			ArrayList<Movie> tempMovies = new ArrayList<Movie>(); // lets add some fake movies
			tempMovies.add(new Movie("Batman", null, "Comic", "A bat struggles to act human"));
			tempMovies.add(new Movie("Manbat", null, "Documentary", "A human struggles to act like a bat"));
			tempMovies.add(new Movie("Scream", null, "boo.", "AAAAAAAAAAAAAAAAAH!"));

			for (Movie m : tempMovies) {
				ArrayList<Showtime> theseShowtimes = new ArrayList<Showtime>(); // lets add some fake showtimes
				theseShowtimes.add(new Showtime(null, 20));
				// and then lets init the seats for these showtimes.
				for (Showtime s : theseShowtimes) {
					s.setSeats();
				}
				m.setShowtime(theseShowtimes);
			}
			
			tempTheatres.get(0).setMovies(tempMovies);

			writeFile(RUsDB, tempRegisteredUsers);
			writeFile(theatresDB, tempTheatres);
			writeFile(moviesDB, tempMovies);
		}
	}

	
	private User verifyLogin(String email, String password) {
		HashMap<Map<String, String>, RegisteredUser> rUsers = new HashMap<Map<String, String>, RegisteredUser>();
		return rUsers.get(new Map<String, String>(email, password));
	}
	
	@SuppressWarnings("unchecked")
	ArrayList<RegisteredUser> importRU() {
		// open RUs file, import all registered users into here.
		return (ArrayList<RegisteredUser>) readFile(RUsDB);
	}

	@SuppressWarnings("unchecked")
	ArrayList<Theatre> importTheatres() {
		// open theatres file, import all into here.
		return (ArrayList<Theatre>) readFile(theatresDB);
	}

	@SuppressWarnings("unchecked")
	ArrayList<Movie> importMovies() {
		return (ArrayList<Movie>) readFile(moviesDB);
	}

	@SuppressWarnings("unchecked")
	ArrayList<Showtime> importShowtimes() { // currently does nothing, as showtimes are tied to a movie.
		return (ArrayList<Showtime>) readFile(showtimesDB);
	}

	ArrayList<Seat> importSeats() { // currently does nothing as seats are tied to a showtime.
		ArrayList<Seat> result = new ArrayList<Seat>();
		return result;
	}

	void setRU(ArrayList<RegisteredUser> registered) {
		writeFile(RUsDB, registered);
	}

	void setTheatres(ArrayList<Theatre> theatres) {
		writeFile(RUsDB, theatres);
	}

	void setMovies(ArrayList<Movie> movies) {
		writeFile(RUsDB, movies);
	}

	void setShowtimes(ArrayList<Showtime> showtimes) { // currently this does nothing, due to the showtimes being
		writeFile(RUsDB, showtimes);
	}

	void setSeats(ArrayList<Seat> seats) {
		writeFile(RUsDB, seats);
	}

	public Object readFile(String file) {
		try {
			ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file));
			Object out = oIn.readObject();
			oIn.close();
			return out;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeFile(String file, Object out) {
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file, false));
			oOut.writeObject(out);
			oOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// if needed, we can also store users and tickets in database, but need to check
	// with yazan first. he said its fine, so we might do that later if needed.
}
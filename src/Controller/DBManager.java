package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Theatre;
import Model.Movie;
import Model.RegisteredUser;
import Model.Seat;
import Model.Showtime;

public class DBManager {
	// a block of constant fixed strings, these are the files that we will use for
	// the db simulation.
	final String RUsDB = "RegisteredUsers.db";
	final String theatresDB = "Theatres.db";
	final String moviesDB = "Movies.db";
	final String showtimesDB = "Showtimes.db";
	final String seatsDB = "Seats.db";

	public static void main(String args[]) {
		// DBManager debug here!

	}

	@SuppressWarnings("serial")
	DBManager() {
		boolean testing = true;
		// if using sql, connect to db here.
		// but we are just using serialized files.
		// so lets go ahead and create some dummy files, so long as they do not exist.
		if(testing) {
			ArrayList<RegisteredUser> tempRegisteredUsers = new ArrayList<RegisteredUser>(); // lets add some good fake
			// users to this file
			tempRegisteredUsers.add(new RegisteredUser("Richard Kickem", "outtaGum", null, null, null, "101 URB St"));
			tempRegisteredUsers.add(new RegisteredUser("Ernest Dude", "password", null, null, null, "444 Cyprus St"));
			tempRegisteredUsers.add(new RegisteredUser("Mann Hecker", "p@5sw0rd", null, null, null, "Earth"));
			tempRegisteredUsers.add(new RegisteredUser("Robert'); DROP TABLE Students;--", "xkcd327!", null, null, null,
			"118 1 Ave NE"));
			ArrayList<Theatre> tempTheatres = new ArrayList<Theatre>(); // lets add some fake theatres
			tempTheatres.add(new Theatre("Chinook", "Calgary", null, null));
			tempTheatres.add(new Theatre("West Edmonton Mall", "Edmonton", null, null));
			ArrayList<Movie> tempMovies = new ArrayList<Movie>(); // lets add some fake movies
			tempMovies.add(new Movie("Batman", "Nov 1 1998", "Comic", "A bat struggles to act human", new ArrayList<Showtime>()));
			tempMovies.add(
					new Movie("Manbat", "Feb 29 1864", "Documentary", "A human struggles to act like a bat", new ArrayList<Showtime>()));
			tempMovies.add(new Movie("Scream", "Mar 13 2001", "boo.", "AAAAAAAAAAAAAAAAAH!", new ArrayList<Showtime>()));
			
			
			ArrayList<Showtime> tempShowtimes = new ArrayList<Showtime>(); // lets add some fake showtimes
			tempShowtimes.add(new Showtime(tempMovies.get(0), new ArrayList<Seat>() {{ add(new Seat("Gamer chair", true, 0, null, 5, 5));}}, "00:00", true));
			tempShowtimes.add(new Showtime(tempMovies.get(0), new ArrayList<Seat>() {{ add(new Seat("False throne", true, 0, null, 5, 5));}}, "11:35", false));
			tempShowtimes.add(new Showtime(tempMovies.get(0), new ArrayList<Seat>() {{ add(new Seat("Basic seat", true, 0, null, 5, 5));}}, "23:15", false));
			tempShowtimes.add(new Showtime(tempMovies.get(1), new ArrayList<Seat>() {{ add(new Seat("IMAX seat", true, 0, null, 5, 5));}}, "20:45", false));
			tempShowtimes.add(new Showtime(tempMovies.get(2), new ArrayList<Seat>() {{ add(new Seat("Bed", true, 0, null, 5, 5));}}, "08:25", true));
			tempShowtimes.add(new Showtime(tempMovies.get(2), new ArrayList<Seat>() {{ add(new Seat("Metal chair", true, 0, null, 5, 5));}}, "17:25", false));

			tempMovies.get(0).getShowtimes().add(tempShowtimes.get(0)); //just complete reference circle of movie/showtimes.
			tempMovies.get(0).getShowtimes().add(tempShowtimes.get(1));
			tempMovies.get(0).getShowtimes().add(tempShowtimes.get(2));
			tempMovies.get(1).getShowtimes().add(tempShowtimes.get(3));
			tempMovies.get(2).getShowtimes().add(tempShowtimes.get(4));
			tempMovies.get(2).getShowtimes().add(tempShowtimes.get(5));
			
			ArrayList<Seat> tempSeats = new ArrayList<Seat>(); // lets add get those fake seats and add them here. hardcoded for now.
			

			writeFile(RUsDB, tempRegisteredUsers);
			writeFile(theatresDB, tempTheatres);
			writeFile(moviesDB, tempMovies);
			writeFile(showtimesDB, tempShowtimes);
			writeFile(seatsDB, tempRegisteredUsers);
			}
	}

	public DBManager(Theatre theatre) {
		
	}
	
	@SuppressWarnings("unchecked")
	ArrayList<RegisteredUser> importRU() {
		// open RUs file, import all registered users into here.
		return (ArrayList<RegisteredUser>) readFile(RUsDB);
	}

	ArrayList<Theatre> importTheatres() {
		// open theatres file, import all into here.
		ArrayList<Theatre> result = new ArrayList<Theatre>();
		return result;
	}

	ArrayList<Movie> importMovies() {
		ArrayList<Movie> result = new ArrayList<Movie>();
		return result;
	}

	ArrayList<Showtime> importShowtimes() {
		ArrayList<Showtime> result = new ArrayList<Showtime>();
		return result;
	}

	ArrayList<Seat> importSeats() {
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

	void setShowtimes(ArrayList<Showtime> showtimes) {
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
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file,false));
			oOut.writeObject(out);
			oOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// if needed, we can also store users and tickets in database, but need to check
	// with yazan first. he said its fine, so we might do that later if needed.
}

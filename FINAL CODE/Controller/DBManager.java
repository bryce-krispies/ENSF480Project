package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.Cart;
import Model.Credit;
import Model.CreditCard;
import Model.Movie;
import Model.RegisteredUser;
import Model.Showtime;
import Model.Ticket;

public class DBManager {
	
	final static String theatreDB = "Theatre.db";
	final static String creditDB = "Credits.db";
	final static String RUsDB = "RegisteredUsers.db";

	@SuppressWarnings("unchecked")
	public static ArrayList<Movie> importTheatre(){
		File f = new File(theatreDB);
		
		if (!f.exists() && !f.isDirectory()) {
			ArrayList<Movie> movies = new ArrayList<Movie>();
			movies.add(new Movie("Blade Runner 2049", LocalDateTime.of(2020, 11, 29, 22, 25), "Science Fiction", 
					"Young Blade Runner K's discovery of a long-buried secret leads him to track "
					+ "down former Blade Runner Rick Deckard, who's been missing for thirty years."));
			movies.add(new Movie("Tropic Thunder", LocalDateTime.of(2020, 11, 30, 1, 50), "Comedy", 
					"Through a series of freak occurrences, a group of actors shooting a big-budget "
					+ "war movie are forced to become the soldiers they are portraying."));
			movies.add(new Movie("Parasite", LocalDateTime.of(2020, 12, 5, 11, 35), "Black Comedy Thriller",
					"Greed and class discrimination threaten the newly formed symbiotic relationship "
					+ "between the wealthy Park family and the destitute Kim clan."));
			
			ArrayList<Showtime> showtimes1 = new ArrayList<Showtime>();
			showtimes1.add(new Showtime(LocalDateTime.now().withHour(12).withMinute(00), 10));
			showtimes1.add(new Showtime(LocalDateTime.now().withHour(18).withMinute(45), 10));
			showtimes1.add(new Showtime(LocalDateTime.now().withHour(22).withMinute(50), 10));
			
			ArrayList<Showtime> showtimes2 = new ArrayList<Showtime>();
			showtimes2.add(new Showtime(LocalDateTime.now().withHour(13).withMinute(25), 10));
			showtimes2.add(new Showtime(LocalDateTime.now().withHour(17).withMinute(00), 10));
			showtimes2.add(new Showtime(LocalDateTime.now().withHour(23).withMinute(55), 10));
			
			ArrayList<Showtime> showtimes3 = new ArrayList<Showtime>();
			showtimes3.add(new Showtime(LocalDateTime.of(2020, 12, 5, 11, 45), 10));
			showtimes3.add(new Showtime(LocalDateTime.of(2020, 12, 5, 14, 10), 10));
			showtimes3.add(new Showtime(LocalDateTime.of(2020, 12, 5, 20, 00), 10));

			movies.get(0).setShowtime(showtimes1);
			movies.get(1).setShowtime(showtimes2);
			movies.get(2).setShowtime(showtimes3);
			
			int j = 0;
			for(Movie m : movies) {
				for(Showtime sh : m.getShowtime()) {
					ArrayList<Ticket> tickets = new ArrayList<Ticket>(10);
					for(int i = 0; i < 10 ; i++) {
						Ticket ticket = new Ticket(Integer.toString(j++), 8.99);
						sh.setMovie(m);
						ticket.setShowtime(sh);
						tickets.add(ticket);
					}
					sh.setTickets(tickets);
					sh.setSeats();
				}
			}
			
			writeFile(theatreDB, movies);
		}
		
		return (ArrayList<Movie>)readFile(theatreDB);
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Credit> importCredits(){
		File f = new File(creditDB);
		if (!f.exists() && !f.isDirectory()) {
			return new ArrayList<Credit>();
		}
		return (ArrayList<Credit>)readFile(creditDB);
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<RegisteredUser> importRUs(){
		File f = new File(RUsDB);
		if (!f.exists() && !f.isDirectory()) {// if the file dos not exist yet
			ArrayList<RegisteredUser> RUs = new ArrayList<RegisteredUser>();
			RUs.add(new RegisteredUser("Bryce Cayanan",  "bryce.cayanan@example.com", "123456qq", new Cart(),
					new CreditCard("1234567890123456", 286, 1000), "Cyprus Street", LocalDateTime.of(2020, 11, 1, 12, 00)));
			RUs.add(new RegisteredUser("Cloud Chagnon", "cchagnon@example.com", "password", new Cart(),
					new CreditCard("1724879283938218", 735, 500000), "Bob's Drive", LocalDateTime.of(2020, 11, 1, 12, 00)));
			writeFile(RUsDB, RUs);
		}
		ArrayList<RegisteredUser> registeredUsers = (ArrayList)readFile(RUsDB);
		// open RUs file, import all registered users into here.
		return registeredUsers;
	}
	
	public static Credit createCredit(String code, double value) {
		ArrayList<Credit> credits = importCredits();
		Credit newCredit = new Credit(code, value, LocalDateTime.now().plusYears(1));
		
		credits.add(newCredit);
		writeFile(creditDB, credits);
		
		return newCredit;
	}
	public static RegisteredUser createUser(String name, String email, String password, CreditCard card, String address) {
		ArrayList<RegisteredUser> registeredUsers = importRUs();
		RegisteredUser newUser = new RegisteredUser(name, email, password, new Cart(), card, address, LocalDateTime.now());
		
		registeredUsers.add(newUser);
		writeFile(RUsDB, registeredUsers);

		return newUser;
	}
	
	public static void updateTheatre(ArrayList<Movie> movies) {
		writeFile(theatreDB, movies);
	}
	public static void updateRUs(ArrayList<RegisteredUser> users) {
		writeFile(RUsDB, users);
	}
	public static void updateCredits(ArrayList<Credit> credits) {
		writeFile(creditDB, credits);
	}
	
	public static Object readFile(String file) {
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
	public static void writeFile(String file, Object out) {
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file, false));
			oOut.writeObject(out);
			oOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package Controller;

import Model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Movie;
import Model.Seat;
import Model.Showtime;
import Model.Theatre;

public class TRS {
	User user;
	Theatre theatre;
	DBManager dbMan;
	ViewController viewCont;
	PaymentController payCont;
	
	public TRS(ViewController viewCont) {
		dbMan = new DBManager(theatre); //Load database
		this.viewCont = viewCont;
		payCont = new PaymentController();
	}
	
	public ArrayList<Movie> getMovieDatabase() {
		ArrayList<Seat> seats1 = new ArrayList<Seat>();
		seats1.add(new Seat(null, "A1", null, true));
		seats1.add(new Seat(null, "B2", null, true));
		seats1.add(new Seat(null, "C3", null, true));
		seats1.add(new Seat(null, "D4", null, true));
		
		ArrayList<Seat> seats2 = new ArrayList<Seat>();
		seats2.add(new Seat(null, "E5", null, true));
		seats2.add(new Seat(null, "F6", null, true));
		seats2.add(new Seat(null, "G7", null, true));
		seats2.add(new Seat(null, "H8", null, true));
		
		ArrayList<Seat> seats3 = new ArrayList<Seat>();
		seats3.add(new Seat(null, "I9", null, true));
		seats3.add(new Seat(null, "J1", null, true));
		seats3.add(new Seat(null, "K2", null, true));
		seats3.add(new Seat(null, "L3", null, true));
		
		ArrayList<Seat> seats4 = new ArrayList<Seat>();
		seats4.add(new Seat(null, "Z4", null, true));
		seats4.add(new Seat(null, "Y5", null, true));
		seats4.add(new Seat(null, "X6", null, true));
		seats4.add(new Seat(null, "W7", null, true));
		
		ArrayList<Showtime> showtimes1 = new ArrayList<Showtime>();
		showtimes1.add(new Showtime(LocalDateTime.of(2020, Month.DECEMBER, 30, 5, 0)));
		showtimes1.add(new Showtime(LocalDateTime.of(2020, Month.DECEMBER, 26, 15, 30)));
		showtimes1.get(0).setSeatList(seats1);
		showtimes1.get(1).setSeatList(seats2);

		ArrayList<Showtime> showtimes2 = new ArrayList<Showtime>();
		showtimes2.add(new Showtime(LocalDateTime.of(2020, Month.NOVEMBER, 18, 5, 0)));
		showtimes2.add(new Showtime(LocalDateTime.of(2020, Month.NOVEMBER, 21, 15, 30)));
		showtimes2.get(0).setSeatList(seats3);
		showtimes2.get(1).setSeatList(seats4);
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("Blade Runner 2077", LocalDateTime.of(2020, Month.DECEMBER, 26, 0, 0), "Sci-Fi", "Synopsis for Blade Runner 2077"));
		movies.add(new Movie("Arrival", LocalDateTime.of(2020, Month.NOVEMBER, 1, 0, 0), "Action", "Synopsis for Arrival"));
		movies.get(0).setShowtime(showtimes1);
		movies.get(1).setShowtime(showtimes2);
		
		theatre = new Theatre("The Pyramid", 1000);
		theatre.setMovies(movies);
		
		return theatre.getMovies();
	}
	
	public boolean addTicketToCart(String movie, String showtime, String seat) {
		int i = 0;
		if(i == 1){//add to cart success
			return true;
		}
		
		//add to cart failed
		
		return false;
	}
	
	public boolean login(String email, String password) {
		
		//Check in database for email and password pair
		
		if(true) {//login successful
			//Set user variable
			
			if(true/* If today's date is past 1 year since the registered user's last payment date */) {
				int choice = JOptionPane.showConfirmDialog(null, 
								"It seems you haven't payed your yearly subscription. Want to renew?", "NOTICE", JOptionPane.YES_NO_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					//Update paymentDate
					//Subtract subscription amount from user's credit card
				} else {
					return false;
				}
			}
			
			return true;
		}
		
		//login unsuccessful (no email, or password doesnt match)
		return false;
	}
	
	public boolean register(String email, String password, String cardNumber, String cvv, String expiryDate, String address) {
		
		//Check in database for email, or cardNumber
		
		if(false /* If email or card number already registered in database */) {
			return false;
		}
		
		if(false /* If cardNumber does not have sufficient funds to pay registration fee */) {
			return false;
		}
		
		//registration successful
		
		//Set user variable
		
		return true;
	}

}

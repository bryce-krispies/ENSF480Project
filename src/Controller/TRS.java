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
		ArrayList<Seat> seats = new ArrayList<Seat>();
		seats.add(new Seat(null, "A1", null, true));
		seats.add(new Seat(null, "B2", null, true));
		seats.add(new Seat(null, "C3", null, true));
		seats.add(new Seat(null, "D4", null, true));
		
		ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
		showtimes.add(new Showtime(LocalDateTime.of(2020, Month.NOVEMBER, 29, 5, 0)));
		showtimes.add(new Showtime(LocalDateTime.of(2020, Month.NOVEMBER, 29, 15, 30)));
		showtimes.add(new Showtime(LocalDateTime.of(2020, Month.NOVEMBER, 29, 0, 45)));
		showtimes.add(new Showtime(LocalDateTime.of(2020, Month.NOVEMBER, 29, 13, 0)));
		showtimes.get(0).setSeatList(seats);
		showtimes.get(1).setSeatList(seats);
		showtimes.get(2).setSeatList(seats);
		showtimes.get(3).setSeatList(seats);
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("Blade Runner 2077", LocalDateTime.of(2020, Month.DECEMBER, 26, 0, 0), "Sci-Fi", "Synopsis for Blade Runner 2077"));
		movies.add(new Movie("Arrival", LocalDateTime.of(2020, Month.NOVEMBER, 1, 0, 0), "Action", "Synopsis for Arrival"));
		movies.add(new Movie("Ex Machina", LocalDateTime.of(2020, Month.NOVEMBER, 29, 0, 0), "Thriller", "Synopsis for Ex Machina"));
		movies.add(new Movie("Annihilation", LocalDateTime.of(2020, Month.NOVEMBER, 18, 0, 0), "Horror", "Synopsis for Annihilation"));
		movies.get(0).setShowtime(showtimes);
		movies.get(1).setShowtime(showtimes);
		movies.get(2).setShowtime(showtimes);
		movies.get(3).setShowtime(showtimes);
		
		theatre = new Theatre("The Pyramid");
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

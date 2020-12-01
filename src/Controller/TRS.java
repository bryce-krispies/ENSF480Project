package Controller;

import Model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Cart;
import Model.Credit;
import Model.CreditCard;
import Model.Movie;
import Model.RegisteredUser;
import Model.Seat;
import Model.Showtime;
import Model.Theatre;
import Model.Ticket;

public class TRS {
	
	private boolean isRegistered;
	private User user;
	private Theatre theatre;
	private DBManager dbMan;
	private ViewController viewCont;
	private PaymentController payCont;
	
	public TRS(ViewController viewCont) {
		isRegistered = false;
		user = new User();
		theatre = new Theatre("Scotiabank Theatre", 50); //TODO Fix the account quantity
		dbMan = new DBManager(theatre);
		this.viewCont = viewCont;
		payCont = new PaymentController();
	}
	
	public ArrayList<Movie> getMovieDatabase() {
		return theatre.getMovies();
	}
	
	public String refundTicket(int id) {
		//TODO Write
		return null;
	}
	
	public boolean addTicketToCart(String movie, String showtime, String seatID) {
		
		Seat s = theatre.getMovie(movie).getSpecificShowtime(showtime).getSeat(seatID);
		
		if(s != null) {
			s.reserveSeat();
			user.getCart().addTicket(s.getTicket());
		}

		return false;
	}
	
	public boolean checkoutCart(CreditCard card, Credit credit) {
		return false;
		//credit can be null
		//call some method in pay controller
	}
	
 	public boolean login(String email, String password) {
		
		User registeredUser = dbMan.verifyLogin(email, password);
		
		if(registeredUser != null) {
			Cart previousCart = user.getCart();
			user = registeredUser;
			user.setCart(previousCart);
			
			//TODO
//			if(true/* If today's date is past 1 year since the registered user's last payment date */) {
//				int choice = JOptionPane.showConfirmDialog(null, 
//								"It seems you haven't payed your yearly subscription. Want to renew?", "NOTICE", JOptionPane.YES_NO_OPTION);
//				if(choice == JOptionPane.YES_OPTION) {
//					//Update paymentDate
//					//Subtract subscription amount from user's credit card
//				} else {
//					return false;
//				}
//			}
			
			//viewCont.getMainMenu().updateLoginView(user.getName());
			isRegistered = true;
			
			return true;
		}
		
		return false;
	}
	
	public boolean register(String name, String email, String password, String cardNumber, String cvv, String expiryDate, String address) {
		
		//TODO Review function
		User registeredUser = dbMan.verifyRegistration(name, email, password, cardNumber, cvv, expiryDate, address, user.getCart());
		
		if(registeredUser == null) {
			return false;
		}
		
		return login(email, password);
	}
	
	public boolean getIsRegistered() {
		return isRegistered;
	}
	
	public User getUser() {
		return user;
	}

}

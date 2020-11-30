package Controller;

import Model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Movie;
import Model.RegisteredUser;
import Model.Seat;
import Model.Showtime;
import Model.Theatre;

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
		theatre = new Theatre("Scotiabank Theatre", 50);
		dbMan = new DBManager(theatre); //Load database
		this.viewCont = viewCont;
		payCont = new PaymentController();
	}
	
	public ArrayList<Movie> getMovieDatabase() {
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
		
		User registeredUser = dbMan.verifyLogin(email, password);
		
		if(registeredUser != null) {
			//Cart previousCart = user.getCart();
			//Transfer cart
			user = registeredUser;
			//user.setCart(previousCart);
			
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
	
	public boolean register(String email, String password, String cardNumber, String cvv, String expiryDate, String address) {
		
		User registeredUser = dbMan.verifyRegistration(email, cardNumber);
		
		if(registeredUser == null) {
			return false;
		}
		
		return login(email, password);
	}
	
	public boolean getIsRegistered() {
		return isRegistered;
	}
	

}

package Controller;

import Model.User;
import Model.Payment;

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
	private ViewController viewCont;
	private PaymentController payCont;

	public TRS(ViewController viewCont) {
		this.viewCont = viewCont;

		isRegistered = false;
		user = new User();
		theatre = new Theatre("Scotiabank Theatre", 500000); //Theatre always starts up with 500000
		payCont = new PaymentController(this);

		theatre.setMovies(DBManager.importTheatre());
	}
	
 	public boolean login(String email, String password) {
 		
 		User registeredUser = null;
 		for(RegisteredUser ru: DBManager.importRUs()) {
 			if (ru.getEmail().equals(email) && ru.getPassword().equals(password)) {
				registeredUser = ru;
			}
 		}

		if (registeredUser != null) {
			Cart previousCart = user.getCart();
			user = registeredUser;
			user.setCart(previousCart);
			
			if(user.getRenewalDate().plusYears(1).isBefore(LocalDateTime.now())) {
				int choice = JOptionPane.showConfirmDialog(null, 
						"It seems you haven't payed your yearly subscription. Want to renew?", "NOTICE", JOptionPane.YES_NO_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					payCont.payAnnualFee(user.getCreditCard());
					user.setRenewalDate(LocalDateTime.now());
				} else {
					return false;
				}
				
			}
			
			viewCont.getMainMenu().updateLoginView(user.getName());
			isRegistered = true;

			return true;
		}

		return false;
	}	
	public boolean register(String name, String email, String password, String cardNumber, String cvv, String address) {
		
		for(RegisteredUser ru : DBManager.importRUs()) {
			if(ru.getEmail().equals(email)) {
				return false;
			}
		}
		
		CreditCard card = null;
		for(CreditCard cc : FinancialInstituteManager.importCreditCards()) {
			if(cc.getNumber().equals(cardNumber) && cc.getCVV() == Integer.parseInt(cvv)) {
				card = cc;
				if(cc.getBalance() < 20) {
					return false;
				}
				break;
			}
		}
		if (card == null) {
			return false;
		}
		
		DBManager.createUser(name, email, password, card, address);
		
		Payment.makePaymentToTheatre(theatre.getAccount(), 20);
		card.setBalance(card.getBalance() - 20);
		
		return login(email, password);
	}
	public boolean addTicketToCart(String movie, String showtime, String seatID) {
		Seat s = theatre.getMovie(movie).getSpecificShowtime(showtime).getSeat(seatID);
		
		if (s != null) {
			s.reserveSeat();
			user.getCart().addTicket(s.getTicket());
			return true;
		}

		return false;
	}
	public Credit validateCredit(String code) {
		for(Credit c : DBManager.importCredits()) {
			if(c.getCode().equals(code)) {
				return c;
			}
		}
		return null;
	}
	public boolean checkoutCart(String cardNumber, int cvv, String creditCode) {
		boolean result = payCont.checkoutCart(user.getCart().getTickets(), cardNumber, cvv, validateCredit(creditCode));
		
		if(result) {
			for(Ticket t : user.getCart().getTickets()) {
				t.getSeat().reserveSeat();
			}
			
			DBManager.updateTheatre(theatre.getMovies());
		}
		
		return result;
	}
	public Credit refundTicket(String ticketID){
		
		Credit credit = null;

		for(Movie m : theatre.getMovies()) {
			for(Showtime sh : m.getShowTime()) {
				for(Seat se : sh.getSeatList()) {
					if(se.getTicket().getID().equals(ticketID)) {
						if(!se.checkAvailablity() && se.getTicket().isRefundable()) {
							credit = payCont.refundTicket(se.getTicket(), isRegistered);
							se.getTicket().cancelTicket();
							DBManager.updateTheatre(theatre.getMovies());
							ArrayList<Credit> credits = DBManager.importCredits();
							credits.add(credit);
							DBManager.updateCredits(credits);
						}
						break;		
					}
				}
			}
		}
		
		return credit;
	}
	
	public ArrayList<Movie> getMovieDatabase() {
		return theatre.getMovies();
	}
	
	public boolean getIsRegistered() {
		return isRegistered;
	}
	public User getUser() {
		return user;
	}
	public Theatre getTheatre() {
		return theatre;
	}
}

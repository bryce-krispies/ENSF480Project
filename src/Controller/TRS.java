package Controller;

import Model.User;
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
<<<<<<< Updated upstream
	
=======

	public ArrayList<Movie> getMovieDatabase() {
		return theatre.getMovies();
	}

	public User getUser() {
		return user;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public String refundTicket(int id) {
		// TODO Write
		return null;
	}

	public boolean addTicketToCart(String movie, String showtime, String seatID) {

		Seat s = theatre.getMovie(movie).getSpecificShowtime(showtime).getSeat(seatID);

		if (s != null) {
			s.reserveSeat();
			user.getCart().addTicket(s.getTicket());
		}

		return false;
	}

	public boolean login(String email, String password) {

		User registeredUser = dbMan.verifyLogin(email, password);

		if (registeredUser != null) {
			Cart previousCart = user.getCart();
			user = registeredUser;
			user.setCart(previousCart);

			// if(true/* If today's date is past 1 year since the registered user's last
			// payment date */) {
			// int choice = JOptionPane.showConfirmDialog(null,
			// "It seems you haven't payed your yearly subscription. Want to renew?",
			// "NOTICE", JOptionPane.YES_NO_OPTION);
			// if(choice == JOptionPane.YES_OPTION) {
			// //Update paymentDate
			// //Subtract subscription amount from user's credit card
			// } else {
			// return false;
			// }
			// }
			// viewCont.getMainMenu().updateLoginView(user.getName());
			isRegistered = true;

			return true;
		}

		return false;
	}

	public boolean register(String email, String password, String cardNumber, String cvv, String expiryDate,
			String address) {

		User registeredUser = dbMan.verifyRegistration(null, email, password, cardNumber, cvv, expiryDate, address,
				user.getCart());

		if (registeredUser == null) {
			return false;
		}

		return login(email, password);
	}

	public boolean getIsRegistered() {
		return isRegistered;
	}

>>>>>>> Stashed changes
}

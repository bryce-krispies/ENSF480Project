package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import Model.Movie;
import View.*;

public class ViewController {
	
	private TRS system;
	private MainMenuGUI mainMenu;
	private TicketRefundGUI refundTicket;
	private CartGUI viewCart;
	private LoginGUI loginMenu;
	private MoviesGUI moviesMenu;
	private TicketPaymentGUI payTicket;
	private RegisterGUI registerMenu;
	
	boolean loggedIn = false;
	//Used to keep track of previously used vouchers so you cant apply the same voucher to
	//the same purchase
	private ArrayList<String> prevVouchers;
	
	public ViewController() {
		system = new TRS(this);
		mainMenu = new MainMenuGUI(570, 150);
		
		mainMenu.addLoginListener(new LoginButtonListener());
		mainMenu.addViewMoviesListener(new ViewMoviesButtonListener());
		mainMenu.addRefundTicketListener(new RefundTicketButtonListener());
		mainMenu.addViewCartListener(new ViewCartButtonListener());
	}
	
	//Main Menu GUI Listeners
	private class LoginButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			loginMenu = new LoginGUI();
			
			loginMenu.addLoginListener(new LoginListener());
			loginMenu.addRegisterListener(new RegisterButtonListener());
		}
	}
	private class ViewMoviesButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ArrayList<Movie> movieDatabase;
			
			if(system.getIsRegistered()) {
				movieDatabase = system.getMovieDatabase();
			}
			else {
				movieDatabase = new ArrayList<Movie>();
				for(Movie m : system.getMovieDatabase()) {
					if(!m.forMembers()) {
						movieDatabase.add(m);
					}
				}
			}
			
			moviesMenu = new MoviesGUI(movieDatabase, system.getIsRegistered());
			
			moviesMenu.addCreateTicketListener(new CreateTicketListener());
		}
	}
	private class RefundTicketButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			refundTicket = new TicketRefundGUI(400, 100);
			refundTicket.addRefundTicketButtonListener(new RefundButtonListener());
		}
	}
	private class ViewCartButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			viewCart = new CartGUI(null);
			
			viewCart.addProceedListener(new ProceedButtonListener());
			viewCart.addExitListener(new CartExitButtonListener());
		}
	}
	
	
	//Login GUI Listeners
	private class LoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
	  		  if(loginMenu.getEmailField().getText().isBlank() || loginMenu.getPasswordField().getPassword().length < 1) {
				  JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  }
			  
	  		  boolean result = system.login(loginMenu.getEmailField().getText(), new String(loginMenu.getPasswordField().getPassword()));
	  		  
			  if(result) {
				  //mainMenu.updateLoginView(user);
				  loginMenu.dispose();
				  return;
			  }
			  
			  JOptionPane.showMessageDialog(null, "Wrong email or password", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private class RegisterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			loginMenu.dispose();
			registerMenu = new RegisterGUI();
			registerMenu.addRegisterListener(new RegisterListener());
		}
	}
	
	
	//Register GUI Listeners
	public class RegisterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
	  		if(registerMenu.getNameField().getText().isBlank() || registerMenu.getEmailField().getText().isBlank() 
	  				|| registerMenu.getPasswordField().getPassword().length < 1 || registerMenu.getCardNumField().getText().isBlank()
	  				|| registerMenu.getCVVField().getText().isBlank() || registerMenu.getExpiryDateField().getText().isBlank() 
	  				|| registerMenu.getAddressField().getText().isBlank()) {
	  			JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
	  			return;
	  		}
	  		
	  		boolean result = system.register(registerMenu.getNameField().getText(), registerMenu.getEmailField().getText(), 
	  								new String(registerMenu.getPasswordField().getPassword()), registerMenu.getCardNumField().getText(), 
	  								registerMenu.getCVVField().getText(), registerMenu.getExpiryDateField().getText(), 
	  								registerMenu.getAddressField().getText());
	  		
			if(result) {
				mainMenu.updateLoginView(registerMenu.getNameField().getText());
				registerMenu.dispose();
				loginMenu.dispose();
				return;
			}

			JOptionPane.showMessageDialog(null, "Sorry, there is either an account with the same email, "
					+ "or your credit card does not exist or doesn't have enough funds", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Movies GUI Listeners
	public class CreateTicketListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean result = system.addTicketToCart(moviesMenu.getMovieNameList().getSelectedValue(), 
								moviesMenu.getShowtimeList().getSelectedValue(), moviesMenu.getSeatList().getSelectedValue());
			
			if(result) {
				JOptionPane.showMessageDialog(null, "Successfully added to cart");
				moviesMenu.dispose();
				return;
			}
			
			JOptionPane.showMessageDialog(null, "Sorry, ticket could not be added to cart", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	//Refund GUI Listeners
	private class RefundButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int ticketID = 0;
			try {
				ticketID = Integer.parseInt(refundTicket.getTicketID());
			}catch (NumberFormatException ex) {
				refundTicket.printErrorMessage("Error: Ticket ID number must be an integer");
				return;
			}
			
			refundTicket.dispose();
			
			if(!system.getIsRegistered()) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to login/register before going ahead?"
						+ "\n(The theatre will only refund 85% of the ticket price if you are unregistered)", "NOTICE", JOptionPane.YES_NO_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					loginMenu = new LoginGUI();
					
					loginMenu.addLoginListener(new LoginListener());
					loginMenu.addRegisterListener(new RegisterButtonListener());
					
					return;
				}
			}

			String result = system.refundTicket(ticketID);
			
			if(result != null) {
				JOptionPane.showMessageDialog(null, "Here is your voucher code: " +result 
						+"\n(Only useable within a year)", "Error", JOptionPane.ERROR_MESSAGE);

				return;
			}
			
			JOptionPane.showMessageDialog(null, "Sorry, ticket could not be refunded. Either your ticket does not exist "
					+ "or your showtime is within 72 hours.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//View Cart Button Listeners
	private class ProceedButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
//			String selection = viewCart.getCartSelection();
//			
//			payTicket = new TicketPaymentGUI(480, 270, selection, loggedIn);
//			payTicket.addVoucherSubmitListener(new SubmitVoucherButtonListener());
//			payTicket.addPayListener(new PayButtonListener());
//			prevVouchers = new ArrayList<String>();
//			if(loggedIn) {
//				//TODO: Get RU payment info
//				String accNum = "478382930458";
//				String CVV = "738";
//				String expDate = "08/22";
//				
//				payTicket.setPaymentInfo(accNum, CVV, expDate);
//			}
		}
	}
	private class CartExitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			viewCart.dispatchEvent(new WindowEvent(viewCart, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	
	//TicketPaymentGUI Listeners
	private class SubmitVoucherButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String voucher = payTicket.getVoucher();
			for(String v : prevVouchers) {
				if(voucher.equals(v)) {
					payTicket.printErrorMessage("Error: You cannot apply the same voucher twice to the price");
					return;
				}
			}
			
			//TODO: Get value of voucher
			double voucherValue = 3;
			
			prevVouchers.add(voucher);
			
			double currentPrice = payTicket.getPrice();
			double newTotal = currentPrice - voucherValue;
			System.out.println(currentPrice);
			if(newTotal < 0) {
				//TODO: Set value of voucher to abs(newTotal) and update database
				voucherValue = Math.abs(newTotal);
				newTotal = 0;
				
			}else {
				//TODO: Remove voucher from database
			}
			
			payTicket.updateTotalDue(newTotal);
		}
	}
	private class PayButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: Add ticket to database and remove item from shopping cart
			//TODO: Check payment info?

			payTicket.displayMessage("Checkout Successful");
			payTicket.dispatchEvent(new WindowEvent(payTicket, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	
	public MainMenuGUI getMainMenu() {
		return mainMenu;
	}
	
	
	public static void main(String [] args) {
		ViewController test = new ViewController();
	}
}

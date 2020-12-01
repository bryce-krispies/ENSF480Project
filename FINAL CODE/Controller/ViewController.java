package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import Controller.ViewController.CreateTicketListener;
import Controller.ViewController.RegisterListener;
import Model.Cart;
import Model.Credit;
import Model.CreditCard;
import Model.Movie;
import Model.Ticket;
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
	
	public ViewController() {
		system = new TRS(this);
		
		mainMenu = new MainMenuGUI(570, 150);
		
		mainMenu.addLoginListener(new LoginButtonListener());
		mainMenu.addViewMoviesListener(new ViewMoviesButtonListener());
		mainMenu.addViewCartListener(new ViewCartButtonListener());
		mainMenu.addRefundTicketListener(new RefundTicketButtonListener());
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
	private class ViewCartButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			viewCart = new CartGUI(system.getUser().getCart().getTickets());
			
			viewCart.addProceedListener(new ProceedButtonListener());
			viewCart.addExitListener(new CartExitButtonListener());
		}
	}
	private class RefundTicketButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			refundTicket = new TicketRefundGUI(400, 100);
			
			refundTicket.addRefundTicketButtonListener(new RefundButtonListener());
		}
	}
	//Login GUI Listeners
	private class LoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
	  		  if(loginMenu.getEmailField().getText().isBlank() || loginMenu.getPasswordField().getPassword().length < 1) {
				  JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  }
			  
	  		  boolean result = system.login(loginMenu.getEmailField().getText(), new String(loginMenu.getPasswordField().getPassword()));
	  		  
			  if(result) {
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
	  				|| registerMenu.getCardNumField().getText().length() != 16 || registerMenu.getCVVField().getText().isBlank() 
	  				|| registerMenu.getCVVField().getText().length() != 3 || registerMenu.getAddressField().getText().isBlank()) {
	  			JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
	  			return;
	  		}
	  		
	  		boolean result = system.register(registerMenu.getNameField().getText(), registerMenu.getEmailField().getText(), 
	  								new String(registerMenu.getPasswordField().getPassword()), registerMenu.getCardNumField().getText(), 
	  								registerMenu.getCVVField().getText(), registerMenu.getAddressField().getText());
	  		
			if(result) {
				registerMenu.dispose();
				return;
			}

			JOptionPane.showMessageDialog(null, "Unsuccessful Registration", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	//Movie GUI Listeners
	public class CreateTicketListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean result = system.addTicketToCart(moviesMenu.getMovieNameList().getSelectedValue(), 
								moviesMenu.getShowtimeList().getSelectedValue(), moviesMenu.getSeatList().getSelectedValue());
			
			if(result) {
				moviesMenu.dispose();
				return;
			}
			
			JOptionPane.showMessageDialog(null, "Sorry, ticket could not be added to cart", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	//View Cart Button Listeners
	private class ProceedButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			viewCart.dispose();

			if(system.getUser().getCart().getTickets().isEmpty()) {
				return;
			}

			payTicket = new TicketPaymentGUI(system.getUser().getCart().getTickets());
			
			if(system.getIsRegistered()) {
				CreditCard userCard = system.getUser().getCreditCard();
				payTicket.setPaymentInfo(userCard.getNumber(), String.valueOf(userCard.getCVV()));
			}
			
			payTicket.addPayListener(new PayButtonListener());
		}
	}
	private class CartExitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			viewCart.dispatchEvent(new WindowEvent(viewCart, WindowEvent.WINDOW_CLOSING));
		}
	}
	//TicketPaymentGUI Listeners
	private class PayButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(payTicket.getCardNumber().length() != 16 || payTicket.getCardNumber().isBlank() 
					|| payTicket.getCVV().isBlank() || payTicket.getCVV().length() != 3) {
				JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
				payTicket.dispose();
				return;
			}
			
			boolean result = system.checkoutCart(payTicket.getCardNumber(), Integer.parseInt(payTicket.getCVV()), payTicket.getCredit());
			
			if(result) {
				payTicket.displayMessage("Checkout Successful");
				system.getUser().clearCart();
				payTicket.dispose();
				return;
			}

			payTicket.displayMessage("Checkout Unsuccessful");
			payTicket.dispatchEvent(new WindowEvent(payTicket, WindowEvent.WINDOW_CLOSING));
		}
	}
	//Refund GUI Listeners
	private class RefundButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(refundTicket.getTicketID().isBlank()) {
				refundTicket.dispose();
				JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
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
			
			Credit result = system.refundTicket(refundTicket.getTicketID());
			
			if(result != null) {
				JOptionPane.showMessageDialog(null, "Here is your voucher code: " +result.getCode() 
						+"\n(Only useable within a year)", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(null, "Sorry, ticket could not be refunded", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public MainMenuGUI getMainMenu() {
		return mainMenu;
	}
	
	public static void main(String [] args) {
		ViewController test = new ViewController();
	}
}

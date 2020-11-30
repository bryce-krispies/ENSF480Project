package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import View.*;

public class ViewController {
	
	private TRS system;
	private MainMenuGUI mainMenu;
	private RefundTicketGUI refundTicket;
	private CartGUI viewCart;
	private LoginGUI loginMenu;
	private MoviesGUI moviesMenu;
	private TicketPaymentGUI payTicket;
	private RegisterGUI registerMenu;
	
	//Temp variable for testing
	private boolean loggedIn;
	
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
			moviesMenu = new MoviesGUI(system.getMovieDatabase());
			
			moviesMenu.addCreateTicketListener(new CreateTicketListener());
		}
	}
	private class RefundTicketButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			refundTicket = new RefundTicketGUI(400, 100);
			refundTicket.addRefundTicketButtonListener(new RefundButtonListener());
		}
	}
	private class ViewCartButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Create view cart GUI here
			viewCart = new CartGUI(500, 400);
			viewCart.addProceedListener(new ProceedButtonListener());
			viewCart.addExitListener(new CartExitButtonListener());
			
			String [] tmpTest = {"Blade Runner 2077, 1:00 PM, A1, $6.99", "Arrival, 7:00 PM, F6, $8.99", "Ex Machina, 10:00 AM, A2, $6.99"};
			viewCart.updateCartGUI(tmpTest);
			//viewCart.updateCartGUI(system.getCartTickets());
		}
	}
	
	
	//Login GUI Listeners
	private class LoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
	  		  if(loginMenu.getEmailField().getText().length() < 1 || loginMenu.getPasswordField().getPassword().length < 1) {
				  JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  }
			  
	  		  boolean result = system.login(loginMenu.getEmailField().getText(), new String(loginMenu.getPasswordField().getPassword()));
	  		  
			  if(result) {
				  mainMenu.updateLoginView(loginMenu.getEmailField().getText());
				  loginMenu.dispose();
				  return;
			  }
			  
			  JOptionPane.showMessageDialog(null, "Wrong email or password", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private class RegisterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			registerMenu = new RegisterGUI();
			registerMenu.addRegisterListener(new RegisterListener());
		}
	}
	
	
	//Register GUI Listeners
	public class RegisterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
	  		if(registerMenu.getEmailField().getText().length() < 1 || registerMenu.getPasswordField().getPassword().length < 1 || registerMenu.getCardNumField().getText().length() < 1 || 
	  			registerMenu.getCVVField().getText().length() < 1 || registerMenu.getExpiryDateField().getText().length() < 1 || registerMenu.getAddressField().getText().length() < 1) {
	  			JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
	  			return;
	  		}
	  		
	  		boolean result = system.register(registerMenu.getEmailField().getText(), new String(registerMenu.getPasswordField().getPassword()), 
	  							registerMenu.getCardNumField().getText(), registerMenu.getCVVField().getText(), 
	  							registerMenu.getExpiryDateField().getText(), registerMenu.getAddressField().getText());
	  		
			if(result) {
				mainMenu.updateLoginView(registerMenu.getEmailField().getText());
				registerMenu.dispose();
				loginMenu.dispose();
				return;
			}
			
			JOptionPane.showMessageDialog(null, "Sorry, there is either an account with the same email, or your credit card does not exist", "Error", JOptionPane.ERROR_MESSAGE);
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
			

			//TODO: Check if tickedID can cancel
			

			if(loggedIn) {
				refundTicket.RUCancel();
				refundTicket.addYesConfButtonListener(new YesRefundButtonListener());
				refundTicket.addNoConfButtonListener(new NoRefundButtonListener());
			}else {
				refundTicket.regCancel();
				refundTicket.addYesConfButtonListener(new YesRefundButtonListener());
				refundTicket.addNoConfButtonListener(new NoRefundButtonListener());
			}
		}
	}
	private class YesRefundButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: Update database/send email with credit
			

			if(loggedIn) {
				refundTicket.displayMessage("Successfully cancelled ticket");
				refundTicket.dispatchEvent(new WindowEvent(refundTicket, WindowEvent.WINDOW_CLOSING));
			}else {
				String email = refundTicket.getEmail();
				
				refundTicket.displayMessage("Successfully cancelled ticket");
				refundTicket.dispatchEvent(new WindowEvent(refundTicket, WindowEvent.WINDOW_CLOSING));
			}
			
		}
	}
	private class NoRefundButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			refundTicket.dispatchEvent(new WindowEvent(refundTicket, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	
	//View Cart Button Listeners
	private class ProceedButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String selection = viewCart.getCartSelection();
			
			payTicket = new TicketPaymentGUI(480, 270, selection, loggedIn);
			payTicket.addVoucherSubmitListener(new SubmitVoucherButtonListener());
			payTicket.addPayListener(new PayButtonListener());
			prevVouchers = new ArrayList<String>();
			if(loggedIn) {
				//TODO: Get RU payment info
				String accNum = "478382930458";
				String CVV = "738";
				String expDate = "08/22";
				
				payTicket.setPaymentInfo(accNum, CVV, expDate);
			}
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
	
	
	public static void main(String [] args) {
		ViewController test = new ViewController();
	}
}

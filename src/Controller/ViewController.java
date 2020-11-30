package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import View.*;

public class ViewController {
	
	private TRS ticketSystem;
	private MainMenuGUI mainMenu;
	
	private LoginGUI loginMenu;
	private RegisterGUI registerMenu;
	private MoviesGUI moviesMenu;
	private TicketPaymentGUI payTicket;
	private RefundTicketGUI refundTicket;
	private CartGUI viewCart;
	
	//Temp variable for testing
	private boolean loggedIn;
	
	//Used to keep track of previously used vouchers so you cant apply the same voucher to
	//the same purchase
	private ArrayList<String> prevVouchers;
	
	public ViewController() {
		ticketSystem = new TRS(this);
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
			moviesMenu = new MoviesGUI();

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
			
			//TODO: Get user cart info (this format of showing the ticket is something I quickly thought up, can change)
			String [] tmpTest = {"Blade Runner 2077, 1:00 PM, A1, $6.99", "Arrival, 7:00 PM, F6, $8.99", "Ex Machina, 10:00 AM, A2, $6.99"};
			viewCart.updateCartGUI(tmpTest);
		}
	}
	
	
	//Login GUI Listeners
	private class LoginListener implements ActionListener{ //TODO Write
		@Override
		public void actionPerformed(ActionEvent e) {
	  		  if(loginMenu.getEmailField().getText().length() < 1 || loginMenu.getPasswordField().getPassword().length < 1) {
				  JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
				  return;
			  }
			  //TODO: Change i into an actual response from viewController
			  int i = 0;
			  if(i == 0) {
//					//TODO: Get login username from loginMenu after logging in
//					mainMenu.updateLoginView("nolanchan1@gmail.com");
//					
//					loggedIn = true;
				  loginMenu.dispose();
			  } else {
				  JOptionPane.showMessageDialog(null, "Wrong email or password", "Error", JOptionPane.ERROR_MESSAGE);
			  }
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
	public class RegisterListener implements ActionListener { //TODO Write
		@Override
		public void actionPerformed(ActionEvent e) {
	  		if(registerMenu.getEmailField().getText().length() < 1 || registerMenu.getPasswordField().getPassword().length < 1 || registerMenu.getCardNumField().getText().length() < 1 || 
	  			registerMenu.getCVVField().getText().length() < 1 || registerMenu.getExpiryDateField().getText().length() < 1 || registerMenu.getAddressField().getText().length() < 1) {
	  			JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
	  			return;
	  		}
	  		
			int i = 0;
			//TODO: Send all inputs back to viewController, and replace i for an actual viewController response
			if(i == 0) {
				JOptionPane.showMessageDialog(null, "Successfully registered");
				registerMenu.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Sorry, there is either an account with the same email, or your credit card does not exist", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	//Movies GUI Listeners
	public class CreateTicketListener implements ActionListener { //TODO Write
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Ticket: " +moviesMenu.getMovieNameList().getSelectedValue() +" at " 
					+moviesMenu.getShowtimeList().getSelectedValue() +" in seat " +moviesMenu.getSeatList().getSelectedValue());
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
	private class SubmitVoucherButtonListener implements ActionListener{  //TODO Write
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
	private class PayButtonListener implements ActionListener{  //TODO Write
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

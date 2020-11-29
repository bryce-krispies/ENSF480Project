package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import View.*;

public class ViewController {
	
	private MainMenuGUI mainMenu;
	private RefundTicketGUI refundTicket;
	private LoginGUI loginMenu;
	private MoviesGUI moviesMenu;
	
	//Temp variable for testing
	private boolean loggedIn;
	
	//Used to keep track of previously used vouchers so you cant apply the same voucher to
	//the same purchase
	private ArrayList<String> prevVouchers;
	
	public ViewController(MainMenuGUI gui) {
		this.mainMenu = gui;
		
		mainMenu.addLoginListener(new LoginButtonListener());
		mainMenu.addViewMoviesListener(new ViewMoviesButtonListener());
		mainMenu.addRefundTicketListener(new RefundTicketButtonListener());
		mainMenu.addViewCartListener(new ViewCartButtonListener());
	}
	
	//Main menu listener classes
	private class LoginButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			loginMenu = new LoginGUI(400, 100);
			
			//TODO: Get login username from loginMenu after logging in
			mainMenu.updateLoginView("nolanchan1@gmail.com");
			
			loggedIn = true;
		}
	}
	private class ViewMoviesButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Create view movies GUI here
			moviesMenu = new MoviesGUI();
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
		MainMenuGUI test = new MainMenuGUI(570, 150);
		ViewController test1 = new ViewController(test);
	}
}

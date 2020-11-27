package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MainMenuGUI;
import View.RefundTicketGUI;

public class ViewController {
	
	private MainMenuGUI mainMenu;
	private RefundTicketGUI refundTicket;
	
	public ViewController(MainMenuGUI gui) {
		this.mainMenu = gui;
		
		mainMenu.addLoginListener(new LoginButtonListener());
		mainMenu.addViewMoviesListener(new ViewMoviesButtonListener());
		mainMenu.addRefundTicketListener(new RefundTicketButtonListener());
		mainMenu.addViewCartListener(new ViewCartButtonListener());
	}
	
	private class LoginButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Create login GUI here
			
			mainMenu.updateLoginView("nolanchan1@gmail.com");
		}
	}
	
	private class ViewMoviesButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Create view movies GUI here
			
			System.out.println("VIEWMOVIES");
		}
	}
	
	private class RefundTicketButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Create refund ticket GUI here
			
			refundTicket = new RefundTicketGUI(400, 100);
			refundTicket.addRefundTicketButtonListener(new RefundButtonListener());
			
		}
	}
	
	private class ViewCartButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Create view cart GUI here
			
			System.out.println("VIEWCART");
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
			}
			
			
			
			//TODO: Check if tickedID can cancel
			
			//TODO: Check if user logged in (currently hardcoded)
			if(true) {
				refundTicket.RUCancel();
			}
		}
	}
	
	public static void main(String [] args) {
		MainMenuGUI test = new MainMenuGUI(570, 150);
		ViewController test1 = new ViewController(test);
	}
}

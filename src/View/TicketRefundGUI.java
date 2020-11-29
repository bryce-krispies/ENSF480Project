package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TicketRefundGUI extends JFrame{
	private JPanel refundGUI;
	
	private JTextArea ticketID;
	private JButton refundTicketButton;
	
	private JButton yesConfirmationButton;
	private JButton noConfirmationButton;
	private JTextArea emailAddress;
	
	public TicketRefundGUI(int width, int height) {
		super("Refund Ticket");
		setSize(width, height);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		refundGUI = new JPanel();
		refundGUI.setLayout(new BoxLayout(refundGUI, BoxLayout.Y_AXIS));
		
		JPanel enterTicket = new JPanel();
		enterTicket.setLayout(new BoxLayout(enterTicket, BoxLayout.X_AXIS));
		
		JLabel enterTicketText = new JLabel("Please enter the ticket ID number here:");
		ticketID = new JTextArea(1, 5);
		enterTicket.add(Box.createRigidArea(new Dimension(5, 0)));
		enterTicket.add(enterTicketText);
		enterTicket.add(Box.createRigidArea(new Dimension(5, 0)));
		enterTicket.add(ticketID);
		enterTicket.add(Box.createRigidArea(new Dimension(5, 0)));
		
		JPanel refundButton = new JPanel();
		refundTicketButton = new JButton("Refund Ticket");
		refundButton.add(refundTicketButton);
		
		refundGUI.add(Box.createRigidArea(new Dimension(0, 5)));
		refundGUI.add(enterTicket);
		refundGUI.add(Box.createRigidArea(new Dimension(0, 5)));
		refundGUI.add(refundButton);
		
		add(refundGUI);
		
		setVisible(true);
	}
	
	public void printErrorMessage(String text) {
		JOptionPane.showMessageDialog(this, text, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void displayMessage(String text) {
		JOptionPane.showMessageDialog(this, text);
	}
	
	public String getTicketID() {
		return ticketID.getText();
	}
	
	public String getEmail() {
		return emailAddress.getText();
	}
	
	public void RUCancel() {
		setSize(500, 160);
		
		
		JPanel confPanel = new JPanel();
		confPanel.setLayout(new BoxLayout(confPanel, BoxLayout.Y_AXIS));
		
		JPanel subButtonPanel = new JPanel();
		subButtonPanel.setLayout(new BoxLayout(subButtonPanel, BoxLayout.X_AXIS));
		subButtonPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel confirmation = new JLabel("Are you sure you want to cancel your ticket for 100% of the ticket price as credit?");
		confirmation.setAlignmentX(CENTER_ALIGNMENT);
		
		yesConfirmationButton = new JButton("Yes");
		noConfirmationButton = new JButton("No");
		subButtonPanel.add(yesConfirmationButton);
		subButtonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		subButtonPanel.add(noConfirmationButton);
		
		confPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		confPanel.add(confirmation);
		confPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		confPanel.add(subButtonPanel);
		confPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		refundGUI.add(confPanel);
		setVisible(true);
	}
	
	public void regCancel() {
		setSize(800, 190);
		
		JPanel confPanel = new JPanel();
		confPanel.setLayout(new BoxLayout(confPanel, BoxLayout.Y_AXIS));
		
		JPanel subButtonPanel = new JPanel();
		subButtonPanel.setLayout(new BoxLayout(subButtonPanel, BoxLayout.X_AXIS));
		subButtonPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel confirmation = new JLabel("Enter your email address below and click \"Yes\" if you want to cancel your ticket for 85% of the ticket price as credit (RU gets 100%)?");
		confirmation.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
		emailPanel.setAlignmentX(CENTER_ALIGNMENT);
		emailAddress = new JTextArea(1, 20);
		emailPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		emailPanel.add(emailAddress);
		emailPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		yesConfirmationButton = new JButton("Yes");
		noConfirmationButton = new JButton("No");
		subButtonPanel.add(yesConfirmationButton);
		subButtonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		subButtonPanel.add(noConfirmationButton);
		
		confPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		confPanel.add(confirmation);
		confPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		confPanel.add(emailPanel);
		confPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		confPanel.add(subButtonPanel);
		confPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		refundGUI.add(confPanel);
		setVisible(true);
	}
	
	public void addRefundTicketButtonListener(ActionListener refundListener) {
		refundTicketButton.addActionListener(refundListener);
	}
	
	public void addYesConfButtonListener(ActionListener yesConfListener) {
		yesConfirmationButton.addActionListener(yesConfListener);
	}
	
	public void addNoConfButtonListener(ActionListener noConfListener) {
		noConfirmationButton.addActionListener(noConfListener);
	}
}

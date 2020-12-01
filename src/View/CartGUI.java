package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.Ticket;

public class CartGUI extends JFrame{
	private JPanel cartPanel;
	
	private JTextArea cartContents;
	
	private JButton exitButton;
	private JButton proceedButton;
	
	private ArrayList<Ticket> tickets;
	
	public CartGUI(ArrayList<Ticket> ticketDatabase) {
		super("View Cart");
		setSize(500, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		tickets = ticketDatabase;
		if(tickets != null && !tickets.isEmpty()) {
			for(Ticket t : tickets) {
				cartContents.append(t.getShowtime().getMovie().getName() +" at " 
						+t.getShowtime().getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy h:mm a"))
						+" in seat " +t.getSeat().getID() +"\n");
			}
		}
		
		cartPanel = new JPanel();
		cartPanel.setLayout(new BorderLayout(5, 5));
		
		JPanel info1 = new JPanel();
		info1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel cartInfo = new JLabel("Your Shopping Cart:");
		cartInfo.setFont(new Font(cartInfo.getFont().getName(), Font.BOLD, 16));
		info1.add(cartInfo);

		cartPanel.add(info1, BorderLayout.NORTH);
		
		cartContents = new JTextArea();
		cartContents.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(cartContents);
		cartPanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel userInput = new JPanel();
		userInput.setLayout(new BorderLayout(5, 5));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		exitButton = new JButton("Exit");
		proceedButton = new JButton("Proceed to Checkout");
		buttonPanel.add(exitButton);
		buttonPanel.add(proceedButton);
		userInput.add(buttonPanel, BorderLayout.SOUTH);
		
		cartPanel.add(userInput, BorderLayout.SOUTH);
		
		add(cartPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void addProceedListener(ActionListener proceedListener) {
		proceedButton.addActionListener(proceedListener);
	}
	
	public void addExitListener(ActionListener exitListener) {
		exitButton.addActionListener(exitListener);
	}
	
}

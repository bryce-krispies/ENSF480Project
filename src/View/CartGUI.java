package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
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
	
	private JComboBox<String> selectMovie;
	
	private ArrayList<Ticket> tickets;
	
	public CartGUI(ArrayList<Ticket> ticketDatabase) {
		super("View Cart");
		setSize(500, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		tickets = ticketDatabase;
		for(Ticket t : tickets) {
			selectMovie.addItem(t.getID());
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
		
		JPanel info2 = new JPanel();
		info2.setAlignmentX(CENTER_ALIGNMENT);
		JLabel checkoutInfo = new JLabel("Select a movie to checkout:");
		info2.add(checkoutInfo);
		userInput.add(info2, BorderLayout.NORTH);
		
		selectMovie = new JComboBox<String>();
		userInput.add(selectMovie, BorderLayout.CENTER);
		
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
	
	public String getCartSelection() {
		return (String)selectMovie.getSelectedItem();
	}
	
//	public void updateCartGUI(String [] tickets) {
//		cartContents.setText(null);
//		selectMovie.removeAllItems();
//		for(String s: tickets) {
//			cartContents.append(s + "\n");
//			selectMovie.addItem(s);
//		}
//	}
	
	
	public void addProceedListener(ActionListener proceedListener) {
		proceedButton.addActionListener(proceedListener);
	}
	
	public void addExitListener(ActionListener exitListener) {
		exitButton.addActionListener(exitListener);
	}
	
}

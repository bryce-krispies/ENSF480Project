import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenuGUI extends JFrame{
	private JPanel loginPanel;
	private JLabel loginInfo;
	private JButton loginButton;
	private JButton viewMoviesButton;
	private JButton refundTicketButton;
	private JButton viewCartButton;
	
	boolean loggedIn;
	public MainMenuGUI(int width, int height) {
		super("Ticket Registration Application");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loggedIn = false;
		
		JPanel loginGUI = new JPanel();
		loginGUI.setLayout(new BoxLayout(loginGUI, BoxLayout.Y_AXIS));
		
		loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));
		
		JPanel loginPan1 = new JPanel();
		loginPan1.setAlignmentX(SwingConstants.LEFT);
		loginInfo = new JLabel("You are currently logged in as: Guest");
		loginInfo.setFont(new Font(loginInfo.getFont().getName(), Font.BOLD, 16));
		loginPan1.add(loginInfo);
		
		JPanel loginPan2 = new JPanel();
		loginPan2.setAlignmentX(SwingConstants.RIGHT);
		loginButton = new JButton("Login");
		loginPan2.add(loginButton);
		
		loginPanel.add(loginPan1);
		loginPanel.add(loginPan2);
		loginGUI.add(loginPanel);
		
		JPanel buttonPanel1 = new JPanel();
		buttonPanel1.setLayout(new GridLayout(1, 2));
		viewMoviesButton = new JButton("View Movies");
		refundTicketButton = new JButton("Refund Ticket");
		buttonPanel1.add(viewMoviesButton);
		buttonPanel1.add(refundTicketButton);
		
		JPanel buttonPanel2 = new JPanel();
		buttonPanel2.setLayout(new GridLayout(1, 1));
		viewCartButton = new JButton("View Cart");
		buttonPanel2.add(viewCartButton);
		
		loginGUI.add(buttonPanel1);
		loginGUI.add(buttonPanel2);
		
		

		add(loginGUI);
		setVisible(true);
	}
	
	public void addLoginListener(ActionListener loginListener) {
		loginButton.addActionListener(loginListener);
	}
	
	public void addViewMoviesListener(ActionListener viewMoviesListener) {
		viewMoviesButton.addActionListener(viewMoviesListener);
	}
	
	public void addRefundTicketListener(ActionListener refundTicketListener) {
		refundTicketButton.addActionListener(refundTicketListener);
	}
	
	public void addViewCartListener(ActionListener viewCartListener) {
		viewCartButton.addActionListener(viewCartListener);
	}
	
	public void updateLoginView(String name) {
		String shortName;
		if(name.length() > 20) {
			shortName = name.substring(0, 20);
			shortName += "...";
		}else {
			shortName = name;
		}
		String newLoginInfo = "You are currently logged in as: " + shortName;
		loginInfo.setText(newLoginInfo);
	}
}

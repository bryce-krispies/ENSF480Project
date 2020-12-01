package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterGUI extends JFrame {
	
	private JTextField nameField;
	private JTextField emailField;
	private JTextField cardNumField;
	private JTextField cvvField;
	private JTextField addressField;
	private JPasswordField passwordField;
	private JButton registerButton;
	
	private static final int FRAME_WIDTH = 380;
	private static final int FRAME_HEIGHT = 250;
	
	//TODO Make nicer
	public RegisterGUI(){
		super("Register");

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
	    JPanel buttonBar = new JPanel();
	    registerButton = new JButton("Register");
	    buttonBar.add(registerButton);
	    add(BorderLayout.SOUTH, buttonBar);
	    
	    JPanel inputArea = new JPanel(new GridLayout(0, 2));
	    JLabel nameLabel = new JLabel("  Enter Name");
	    nameField = new JTextField(10);
	    JLabel emailLabel = new JLabel("  Enter Email");
	    emailField = new JTextField(10);
	    JLabel passwordLabel = new JLabel("  Enter Password");
	    passwordField = new JPasswordField(10);
	    JLabel cardNoLabel = new JLabel("  Enter Card Number");
	    cardNumField = new JTextField(10);
	    JLabel cvvLabel = new JLabel("  Enter Card Verification Value");
	    cvvField = new JTextField(10);
	    JLabel addressLabel = new JLabel("  Enter Home Address");
	    addressField = new JTextField(10);
	    inputArea.add(nameLabel);
	    inputArea.add(nameField);
     	inputArea.add(emailLabel);
	    inputArea.add(emailField);
	    inputArea.add(passwordLabel);
	    inputArea.add(passwordField);
	    inputArea.add(cardNoLabel);
	    inputArea.add(cardNumField);
	    inputArea.add(cvvLabel);
	    inputArea.add(cvvField);
	    inputArea.add(addressLabel);
	    inputArea.add(addressField);
	    add(BorderLayout.CENTER, inputArea);
		
		setVisible(true);
	}
		
	public void addRegisterListener(ActionListener a) { registerButton.addActionListener(a); }
	
	public JTextField getNameField() { return nameField; }
	public JTextField getEmailField() { return emailField; }
	public JTextField getCardNumField() { return cardNumField; }
	public JTextField getCVVField() { return cvvField; }
	public JTextField getAddressField() { return addressField; }
	public JPasswordField getPasswordField() { return passwordField; }
	public JButton getRegisterButton() { return registerButton; }
	
}





















/*
	public void renderComponents(JPanel panel) {
		renderEmail(panel);
		renderPassword(panel);
		renderCardNum(panel);
		renderCVV(panel);
		renderExpiryDate(panel);
		renderAddress(panel);
	}
	
	public void renderEmail(JPanel panel) {
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font(emailLabel.getFont().getName(), Font.BOLD, 16));
		emailLabel.setBounds(35, 25, 90, 25);
		panel.add(emailLabel);
		
		emailField = new JTextField(10);
		emailField.setBounds(35, 25, 200, 25);
	}
	
	public void renderPassword(JPanel panel) {
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font(passwordLabel.getFont().getName(), Font.BOLD, 16));
		passwordLabel.setBounds(35, 60, 130, 25);
		panel.add(passwordLabel);
	}

	public void renderCardNum(JPanel panel) {
		JLabel cardNumLabel = new JLabel("Card Number");
		cardNumLabel.setFont(new Font(cardNumLabel.getFont().getName(), Font.BOLD, 16));
		cardNumLabel.setBounds(35, 95, 200, 25);
		panel.add(cardNumLabel);
	}

	public void renderCVV(JPanel panel) {
		JLabel cvvLabel = new JLabel("Enter Email");
		cvvLabel.setFont(new Font(cvvLabel.getFont().getName(), Font.BOLD, 16));
		cvvLabel.setBounds(325, 25, 200, 25);
		panel.add(cvvLabel);
	}
	
	public void renderExpiryDate(JPanel panel) {
		JLabel expiryDateLabel = new JLabel("Enter Email");
		expiryDateLabel.setFont(new Font(expiryDateLabel.getFont().getName(), Font.BOLD, 16));
		expiryDateLabel.setBounds(325, 25, 200, 25);
		panel.add(expiryDateLabel);
	}
	
	public void renderAddress(JPanel panel) {
		JLabel addressLabel = new JLabel("Enter Email");
		addressLabel.setFont(new Font(addressLabel.getFont().getName(), Font.BOLD, 16));
		addressLabel.setBounds(325, 25, 200, 25);
		panel.add(addressLabel);
	}
*/

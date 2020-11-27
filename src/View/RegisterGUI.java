package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterGUI {
	
	private static final int FRAME_WIDTH = 380;
	private static final int FRAME_HEIGHT = 250;
	   
	public static void render() 
	{
		
		JFrame frame = new JFrame("Register User");
	    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	      
	    JPanel buttonBar = new JPanel();
	    JButton registerButton = new JButton("Register");
	    buttonBar.add(registerButton);
	    frame.getContentPane().add(BorderLayout.SOUTH, buttonBar);
	      
	    JPanel inputArea = new JPanel(new GridLayout(0, 2));
	    JLabel emailLabel = new JLabel("  Enter Email");
	    JTextField emailField = new JTextField(10);
	    JLabel passwordLabel = new JLabel("  Enter Password");
	    JPasswordField passwordField = new JPasswordField(10);
	    JLabel cardNoLabel = new JLabel("  Enter Card Number");
	    JTextField cardNoField = new JTextField(10);
	    JLabel cvvLabel = new JLabel("  Enter Card Verification Value");
	    JTextField cvvField = new JTextField(10);
	    JLabel expiryDateLabel = new JLabel("  Enter Expiry Date");
	    JTextField expiryDateField = new JTextField(10);
	    JLabel addressLabel = new JLabel("  Enter Home Address");
	    JTextField addressField = new JTextField(10);
     	inputArea.add(emailLabel);
	    inputArea.add(emailField);
	    inputArea.add(passwordLabel);
	    inputArea.add(passwordField);
	    inputArea.add(cardNoLabel);
	    inputArea.add(cardNoField);
	    inputArea.add(cvvLabel);
	    inputArea.add(cvvField);
	    inputArea.add(expiryDateLabel);
	    inputArea.add(expiryDateField);
	    inputArea.add(addressLabel);
	    inputArea.add(addressField);
	    frame.getContentPane().add(BorderLayout.CENTER, inputArea);
	     
	    registerButton.addActionListener(new ActionListener(){
	  	  	@Override
	  	  	public void actionPerformed(ActionEvent e) {
	  	  		if(emailField.getText().length() < 1 || passwordField.getPassword().length < 1 || cardNoField.getText().length() < 1 || 
	  	  				cvvField.getText().length() < 1 || expiryDateField.getText().length() < 1 || addressField.getText().length() < 1) {
	  	  			JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
	  	  			return;
	  	  		}
	  	  		
  	  			int i = 0;
  	  			//SEND all inputs
  	  			if(i == 0) {
  	  				JOptionPane.showMessageDialog(null, "Successfully registered");
  	  				frame.dispose();
  	  			} else {
  	  				JOptionPane.showMessageDialog(null, "Sorry, there is either an account with the same email, or your credit card does not exist", "Error", JOptionPane.ERROR_MESSAGE);
  	  			}
	  	  	}
	    });
	      
	    frame.setVisible(true);
	}
}

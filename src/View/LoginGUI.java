package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI {
	
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 100;
	   
	public static void render() 
	{
	      JFrame frame = new JFrame("Login User");
	      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      frame.setLocationRelativeTo(null);
	      
	      JPanel buttonBar = new JPanel();
	      JButton loginButton = new JButton("Login");
	      JLabel registerLabel = new JLabel("             New Customer?");
	      JButton registerButton = new JButton("Register");
	      buttonBar.add(loginButton);
	      buttonBar.add(registerLabel);
	      buttonBar.add(registerButton);
	      frame.getContentPane().add(BorderLayout.SOUTH, buttonBar);
	      
	      JPanel inputArea = new JPanel();
	      JLabel emailLabel = new JLabel("Enter Email");
	      JTextField emailField = new JTextField(10);
	      JLabel passwordLabel = new JLabel("Enter Password");
	      JPasswordField passwordField = new JPasswordField(10);
	      inputArea.add(emailLabel);
	      inputArea.add(emailField);
	      inputArea.add(passwordLabel);
	      inputArea.add(passwordField);
	      frame.getContentPane().add(BorderLayout.CENTER, inputArea);
	      
	      loginButton.addActionListener(new ActionListener(){
	    	  @Override
		        public void actionPerformed(ActionEvent e) {
	    		  if(emailField.getText().length() < 1 || passwordField.getPassword().length < 1) {
	    			  JOptionPane.showMessageDialog(null, "You must have an input for all fields", "Error", JOptionPane.ERROR_MESSAGE);
	    			  return;
	    		  }
	    		  
	    		  int i = 0;
	    		  if(i == 0) {
	    			  frame.dispose();
	    		  } else {
	    			  JOptionPane.showMessageDialog(null, "Wrong email or password", "Error", JOptionPane.ERROR_MESSAGE);
	    		  }
		        }
	      });
	      
	      registerButton.addActionListener(new ActionListener(){
	    	  @Override
		        public void actionPerformed(ActionEvent e) {
		        	RegisterGUI.render();
		        }
	      });
	      
	      frame.setVisible(true);
		
	}
}


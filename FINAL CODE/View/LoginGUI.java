package View;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class LoginGUI extends JFrame{
	
	private JButton loginButton;
	private JButton registerButton;
	private JTextField emailField;
	private JPasswordField passwordField;
	
	public LoginGUI() {
		super("Login");
		setSize(450, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);

		JLabel userLabel = new JLabel("Email");
		userLabel.setFont(new Font(userLabel.getFont().getName(), Font.BOLD, 16));
		userLabel.setBounds(75, 25, 80, 25);
		panel.add(userLabel);

		emailField = new JTextField(40);
		emailField.setBounds(165, 25, 200, 25);
		panel.add(emailField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font(passwordLabel.getFont().getName(), Font.BOLD, 16));
		passwordLabel.setBounds(75, 75, 80, 25);
		panel.add(passwordLabel);

		passwordField = new JPasswordField(40);
		passwordField.setBounds(165, 75, 200, 25);
		panel.add(passwordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(50, 120, 120, 25);
		panel.add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(225, 120, 160, 25);
		panel.add(registerButton);

		setVisible(true);
	}
	
	public void addLoginListener(ActionListener a) { loginButton.addActionListener(a); }
	public void addRegisterListener(ActionListener a) { registerButton.addActionListener(a); }
	
	public JButton getLoginButton() {return loginButton;}
	public JButton getRegisterButton() {return registerButton;}
	public JTextField getEmailField() {return emailField;}
	public JPasswordField getPasswordField() {return passwordField;}
}




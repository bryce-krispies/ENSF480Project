package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TicketPaymentGUI extends JFrame{
	private JPanel paymentPanel;
	private JPanel voucherPanel;
	
	private JLabel paymentInfo;
	private JTextArea voucherCode;
	private JButton voucherSubmitButton;
	
	private JTextArea cardNumber;
	private JTextArea CVV;
	private JTextArea expiryDate;
	private JButton payButton;
	
	private double totalPrice;
	private boolean registered;
	
	private ArrayList<Ticket> userCart;
	
	public TicketPaymentGUI(int width, int height, ArrayList<Ticket> cart, boolean reg) {
		super("Pay Ticket");
		setSize(width, height);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		paymentPanel = new JPanel();
		paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
		
		userCart = cart;
		registered = reg;
		
		totalPrice = 0;
		for(Ticket t: userCart) {
			totalPrice += t.getPrice();
		}
		
		paymentInfo = new JLabel("Total Due: $" + totalPrice);
		paymentInfo.setFont(new Font(paymentInfo.getFont().getName(), Font.BOLD, 16));
		paymentInfo.setAlignmentX(CENTER_ALIGNMENT);
		paymentPanel.add(paymentInfo);
		paymentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		voucherPanel = new JPanel();
		JLabel voucherEnter = new JLabel("Enter voucher code here:");
		voucherPanel.add(voucherEnter);
		voucherCode = new JTextArea(1, 10);
		voucherPanel.add(voucherCode);
		voucherSubmitButton = new JButton("Apply Voucher");
		voucherPanel.add(voucherSubmitButton);
		paymentPanel.add(voucherPanel);
		
		JLabel enterPaymentInfo = new JLabel("Please enter/confirm you payment information below");
		enterPaymentInfo.setFont(new Font(enterPaymentInfo.getFont().getName(), Font.BOLD, 13));
		enterPaymentInfo.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel paymentSubPan1 = new JPanel();
		paymentSubPan1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel enterCardNumber = new JLabel("Card number:");
		cardNumber = new JTextArea(1, 16);
		paymentSubPan1.add(Box.createRigidArea(new Dimension(70, 0)));
		paymentSubPan1.add(enterCardNumber);
		paymentSubPan1.add(cardNumber);
		
		JPanel paymentSubPan2 = new JPanel();
		paymentSubPan2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel enterCVV = new JLabel("CVV:");
		CVV = new JTextArea(1, 3);
		paymentSubPan2.add(Box.createRigidArea(new Dimension(70, 0)));
		paymentSubPan2.add(enterCVV);
		paymentSubPan2.add(CVV);
		
		JPanel paymentSubPan3 = new JPanel();
		paymentSubPan3.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel enterExpiryDate = new JLabel("Expiry Date:");
		expiryDate = new JTextArea(1, 5);
		paymentSubPan3.add(Box.createRigidArea(new Dimension(70, 0)));
		paymentSubPan3.add(enterExpiryDate);
		paymentSubPan3.add(expiryDate);
		
		payButton = new JButton("Pay");
		payButton.setAlignmentX(CENTER_ALIGNMENT);
		
		paymentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		paymentPanel.add(enterPaymentInfo);
		paymentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		paymentPanel.add(paymentSubPan1);
		paymentPanel.add(paymentSubPan2);
		paymentPanel.add(paymentSubPan3);
		paymentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		paymentPanel.add(payButton);
		
		add(paymentPanel);
		setVisible(true);
	}
	
	public String getVoucher() {
		return voucherCode.getText();
	}
	public String getCardNumber() {
		return cardNumber.getText();
	}
	public String getCVV() {
		return CVV.getText();
	}
	public String getExpiryDate() {
		return expiryDate.getText();
	}
	
	public void updateTotalDue(double newTotal) {
		totalPrice = newTotal;
		paymentInfo.setText("Total Due: $" + totalPrice);
	}
	
	public double getPrice() {
		return totalPrice;
	}
	
	public void setPaymentInfo(String accNum, String cvv, String expDate) {
		cardNumber.setText(accNum);
		cardNumber.setEditable(false);
		
		CVV.setText(cvv);
		CVV.setEditable(false);
		
		expiryDate.setText(expDate);
		expiryDate.setEditable(false);
	}
	
	public void displayMessage(String text) {
		JOptionPane.showMessageDialog(this, text);
	}
	public void printErrorMessage(String text) {
		JOptionPane.showMessageDialog(this, text, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void addVoucherSubmitListener(ActionListener voucherSubmitListener) {
		voucherSubmitButton.addActionListener(voucherSubmitListener);
	}
	public void addPayListener(ActionListener payListener) {
		payButton.addActionListener(payListener);
	}
}

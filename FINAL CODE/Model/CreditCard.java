package Model;

import java.io.Serializable;

public class CreditCard implements Serializable{

	private static final long serialVersionUID = 6085296104233230803L;
	private String number;
	private int CVV;
	private double balance;

	public CreditCard(String number, int CVV, double balance) {
		this.setNumber(number);
		this.setCVV(CVV);
		this.setBalance(balance);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
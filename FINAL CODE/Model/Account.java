package Model;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 1585844121655089695L;
	private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void updateBalance(double amountToUpdate) {
        balance += amountToUpdate;

    }
}
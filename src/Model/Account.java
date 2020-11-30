package Model;

public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void updateBalance(double amountToUpdate) {
        balance += amountToUpdate;

    }
}
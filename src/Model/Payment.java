package Model;

import java.io.Serializable;

import Controller.PaymentController;

public class Payment implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7108942008533232865L;
    private boolean hasCompleted;
    private PaymentController paymentController;
    private Account account;

    public Payment() {
        hasCompleted = false;
    }

    public void makePaymentToTheatre(Account account, double total) {
        account.updateBalance(total);
    }

    public void refundPaymentToUser(double amountRefunded) {

        account.updateBalance(-amountRefunded);
    }

}
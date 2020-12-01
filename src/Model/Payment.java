package Model;

import java.io.Serializable;

import Controller.PaymentController;

public class Payment implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7108942008533232865L;

    public void makePaymentToTheatre(Account account, double total) {
        account.updateBalance(total);
    }

    public void refundPaymentToUser(Account account, double amountRefunded) {

        account.updateBalance(-amountRefunded);
    }

}
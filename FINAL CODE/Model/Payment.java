package Model;

import java.io.Serializable;

public class Payment implements Serializable {

    private static final long serialVersionUID = 7108942008533232865L;

    public static void makePaymentToTheatre(Account account, double total) {
        account.updateBalance(total);
    }

    public static void refundPaymentToUser(Account account, double amountRefunded) {
        account.updateBalance(-amountRefunded);
    }

}
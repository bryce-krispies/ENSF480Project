package Model;

import Controller.PaymentController;

public class Payment {
    private boolean hasCompleted;
    private PaymentController paymentController;
    private Account account;

    public Payment() {
        hasCompleted = false;
    }

    public void makePaymentToTheatre(Account account, float total) {
        account.updateBalance(total);
    }

    public void refundPaymentToUser(Ticket ticket) {
        ticket.cancelTicket();
        account.updateBalance(-ticket.getPrice());
    }

}
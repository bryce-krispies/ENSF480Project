public class Payment {
    private boolean hasCompleted;
    private PaymentController paymentController;
    private Account account;

    public Payment() {
        hasCompleted = false;
    }

    public void makePaymentToTheatre(Account account, double total) {
        account.updateBalance(total);
    }

    public void refundPaymentToUser(double amountToRefund) {

        account.updateBalance(amountToRefund);
    }

}
package Controller;

import Model.Credit;
import Model.Payment;
import Model.Ticket;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;
import java.security.SecureRandom;
import java.time.LocalDateTime;

public class PaymentController {
    private TRS trs;
    private Payment payment;
    private FinancialInstituteManager fim;
    private final double annualFee = 20.00;

    public PaymentController() {
        fim = new FinancialInstituteManager();
    }

    public boolean purchaseTicket(CreditCard creditCard, Credit credit) {
        if (!fim.validateNumber(creditCard))
            return false;

        for (Ticket t : trs.getUser().getCart().getTickets()) {
            double total = t.getPrice();

            if (credit != null && credit.isValid()) {
                if (credit.getValue() <= total) {
                    total -= credit.getValue();
                    credit.updateValue(credit.getValue());
                } else {
                    credit.updateValue(total);
                    total = 0;
                }
            }
            payment.makePaymentToTheatre(trs.getTheatre().getAccount(), total);
        }

        return true;
    }

    public boolean payAnnualFee(CreditCard creditCard) {

        if (!fim.validateNumber(creditCard))
            return false;

        payment.makePaymentToTheatre(trs.getTheatre().getAccount(), annualFee);
        return true;
    }

    public Credit refundTicket(Ticket ticket) {
        double creditAmount = 0;

        creditAmount = ticket.getPrice();
        if (!trs.getIsRegistered()) {
            creditAmount /= 0.85;
        }
        payment.refundPaymentToUser(creditAmount);
        ticket.cancelTicket();

        Credit credit = new Credit(createRandomCode((int) Math.random(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ"), creditAmount,
                LocalDateTime.now().plusYears(1));

        return credit;

    }

    public void setPayment(Payment p) {
        payment = p;
    }

    private String createRandomCode(int codeLength, String id) {
        List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
        Collections.shuffle(temp, new SecureRandom());
        return temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining());
    }

}

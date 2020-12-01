package Controller;

import Model.Credit;
import Model.CreditCard;
import Model.Payment;
import Model.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.UUID;
import java.security.SecureRandom;
import java.time.LocalDateTime;

public class PaymentController {
	
    private TRS trs;
    private final double annualFee = 20.00;

    public PaymentController(TRS trs) {
    	this.trs = trs;
    }
    
    public boolean checkoutCart(ArrayList<Ticket> cart, String cardNumber, int cvv, Credit credit) {
    	
    	CreditCard card = null;
    	for(CreditCard cc : FinancialInstituteManager.importCreditCards()) {
    		if(cc.getNumber().equals(cardNumber) && cc.getCVV() == cvv) {
    			card = cc;
    		}
    	}
    	if(card == null) {
    		return false;
    	}
    	
    	double totalPrice = 0;
    	for(Ticket t : cart) {
    		totalPrice += t.getPrice();
    	}
    	if(totalPrice > card.getBalance()) {
    		return false;
    	}
    	
    	for(Ticket t : cart) {
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
    	}
    	Payment.makePaymentToTheatre(trs.getTheatre().getAccount(), totalPrice);
    	
    	ArrayList<CreditCard> creditCards = FinancialInstituteManager.importCreditCards();
    	for(CreditCard cc : creditCards) {
    		if(card.getNumber().equals(cc.getNumber())) {
    			cc.setBalance(-totalPrice);
    			break;
    		}
    	}

    	FinancialInstituteManager.updateCreditCards(creditCards);
    	
    	return true;
    }
    public Credit refundTicket(Ticket ticket, boolean isRegistered) {
        double creditAmount = 0;

        creditAmount = ticket.getPrice();
        if (!isRegistered) {
            creditAmount /= 0.85;
        }

        Payment.refundPaymentToUser(trs.getTheatre().getAccount(), creditAmount);
        return new Credit(UUID.randomUUID().toString(), creditAmount, LocalDateTime.now());
    }
    public boolean payAnnualFee(CreditCard creditCard) {
        if (creditCard.getBalance() < annualFee)
            return false;

        creditCard.setBalance(creditCard.getBalance() - annualFee);
        Payment.makePaymentToTheatre(trs.getTheatre().getAccount(), annualFee);
        return true;
    }
}

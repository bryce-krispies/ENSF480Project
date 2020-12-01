package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6991294194561132682L;
	private Showtime showTime;
    private Seat seat;
    private String id;
    private double price;
    private LocalDateTime purchaseTime;

    // constructor

    public Ticket(String id, double price) {
        this.id = id;
        this.price = price;
    }

    // Getters & setters

    public Showtime getShowtime() {
        return showTime;
    }

    public void setShowtime(Showtime st) {
        showTime = st;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat s) {
        seat = s;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void cancelTicket() {
        seat.freeSeat();
        purchaseTime = null;
    }
    
    public void purchaseTicket() {
        purchaseTime = LocalDateTime.now();
        seat.reserveSeat();
    }

    public boolean isRefundable() {
        return (purchaseTime.isBefore(showTime.getTime().minusHours(72)));
    }
	
    @Override
    public String toString() {
        String res = "";
        res += "Ticket ID ";
        res += id;
        res += ": ";
        res += this.getShowtime().getMovie().getName();
        res += ", ";
        res += this.getShowtime().getTime().format(DateTimeFormatter.ofPattern("h:mm a"));
        res += ", ";
        res += this.getSeat().getID();
        res += ", $";
        res += this.getPrice();
        return res;
    }

}

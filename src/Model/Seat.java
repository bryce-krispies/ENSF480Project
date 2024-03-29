package Model;

import java.io.Serializable;

public class Seat implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3945050414515362123L;

	public enum type {
        forRegUser, forEveryone
    }

    private String id;
    private boolean isAvilable;

    private type seatType;

    private Ticket ticket;

    public Seat(Ticket ticket, String id, type t, boolean isAvilable) {
        this.ticket = ticket;
        this.isAvilable = isAvilable;
        this.id = id;
        this.seatType = t;
    }

    public String getID() {
        return id;
    }

    public boolean checkAvailablity() {
        return isAvilable;
    }

    public type getEnumType() {
        return seatType;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void reserveSeat() {
        isAvilable = false;
    }

    public void freeSeat() {
        isAvilable = true;
    }

}
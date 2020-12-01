package Model;


import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{

	private static final long serialVersionUID = -4334387528494765172L;
	ArrayList<Ticket> tickets;

	public Cart() {
		tickets = new ArrayList<Ticket>();
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
} 


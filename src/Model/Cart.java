package Model;

import java.util.ArrayList;

public class Cart {
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
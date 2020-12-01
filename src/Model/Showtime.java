package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Showtime {
    private Movie movie;
    private ArrayList<Ticket> tickets;
    private ArrayList<Seat> seats;
    private LocalDateTime time;
    private boolean isFull;
    private int numOfCustomers;
    
    public Showtime(LocalDateTime time, int seatNo) {
        this.time = time;
        isFull = false;
        numOfCustomers = seatNo;
        seats = new ArrayList<Seat>(numOfCustomers);
        tickets = new ArrayList<Ticket>(numOfCustomers);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie m) {
        movie = m;
    }

    public ArrayList<Seat> getSeatList() {
        return seats;
    }
    
    public void setSeatList(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public Seat getSeat(String id) {
        for (Seat seat : seats) {
            if (seat.getID() == id)
                return seat;
        }
        return null;
    }

    public void setSeats() {
        int i = 0;
        for (; i < (numOfCustomers * 0.9); i++) {
            Seat seat = new Seat(tickets.get(i), Integer.toString(i), Seat.type.forEveryone, true);
            seats.add(seat);
            tickets.get(i).setSeat(seat);
        }
        
        for (; i < numOfCustomers; i++) {
            Seat seat = new Seat(tickets.get(i), Integer.toString(i), Seat.type.forRegUser, true);
            seats.add(seat);
            tickets.get(i).setSeat(seat);
        }
    }
    
    public void setTickets(ArrayList<Ticket> tickets) {
    	this.tickets = tickets;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public boolean checkIsFull() {
        for (Seat seat : seats)
            if (seat.checkAvailablity() == true) {
                isFull = false;
                return isFull;
            }
        isFull = true;
        return isFull;

    }

}

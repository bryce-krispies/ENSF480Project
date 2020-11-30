import java.time.LocalDateTime;
import java.util.ArrayList;

public class Showtime {
    private Movie movie;
    private ArrayList<Ticket> tickets;
    private ArrayList<Seat> seats;
    private LocalDateTime time;
    private boolean isFull;

    public Showtime(LocalDateTime time) {
        this.time = time;
        isFull = false;
        seats = new ArrayList<Seat>(20);
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

    // Might not need this
    public Seat getSeat(String id) {
        for (Seat seat : seats) {
            if (seat.getID() == id)
                return seat;
        }
        return null;
    }

    public void setSeats() {
        int i = 0;
        for (; i < (seats.size() * 0.9); i++) {
            Seat seat = new Seat(tickets.get(i), Integer.toString(i), Seat.type.forEveryone);
            seats.add(seat);
            tickets.get(i).setSeat(seat);
        }

        for (; i < seats.size(); i++) {
            Seat seat = new Seat(tickets.get(i), Integer.toString(i), Seat.type.forRegUser);
            seats.add(seat);
            tickets.get(i).setSeat(seat);
        }
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
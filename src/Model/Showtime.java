package Model;

import java.util.ArrayList;

public class Showtime{
	public String time;
	public ArrayList<Seat> seats;
	
	Showtime(String time, String[] seats){
		this.time = time;
		this.seats = new ArrayList<Seat>();
		for(int i = 0; i < seats.length; i++) {
			this.seats.add(new Seat(seats[i]));
		}
	}
}
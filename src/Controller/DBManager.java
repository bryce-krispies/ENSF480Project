package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Movie;
import Model.RegisteredUser;
import Model.Seat;
import Model.Showtime;
import Model.Theatre;


public class DBManager {
	public static void main(String args[]) {
		//DBManager debug here!
		
	}
	DBManager(){
		//if using sql, connect to db here.
		//but we are just using serialized files.
	}
	ArrayList<RegisteredUser> importRU(){
		//open RUs file, import all registered users into here.
		ArrayList<RegisteredUser> result = (ArrayList<RegisteredUser>) readFile("RegisteredUsers.db") ;
		return result;
	}
	ArrayList<Theatre> importTheatres(){
		//open theatres file, import all into here.
		ArrayList<Theatre> result = new ArrayList<Theatre>();
		return result;
	}
	ArrayList<Movie> importMovies(){
		ArrayList<Movie> result = new ArrayList<Movie>();
		return result;
	}
	ArrayList<Showtime> importShowtimes(){
		ArrayList<Showtime> result = new ArrayList<Showtime>();
		return result;
	}
	ArrayList<Seat> importSeats(){
		ArrayList<Seat> result = new ArrayList<Seat>();
		return result;
	}
	
	public Object readFile(String file) {
		try {
			ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file));
			Object out = oIn.readObject();
			oIn.close();
			return out;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void writeFile(String file, Object out) {
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file));
			oOut.writeObject(out);
			oOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//if needed, we can also store users and tickets in database, but need to check with yazan first.
}

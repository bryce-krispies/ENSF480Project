package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import Model.Movie;
import Model.Showtime;
import Model.Seat;

public class MoviesGUI extends JFrame{
	
	private ArrayList<Movie> movies;
	private Movie selectedMovie;
	private Showtime selectedShowtime;
	
	private JList<String> movieNameList;
	private JList<String> showtimeList;
	private JList<String> seatList;
	private JTextArea output;
	private JButton createTicketButton;
	
	public MoviesGUI(ArrayList<Movie> movieDatabase) {
		super("Catalogue");
		
		movies = movieDatabase;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        String[] movieNames = new String[movies.size()];
        for(int i = 0; i < movies.size(); i++) {
        	movieNames[i] = movies.get(i).getName();
        }
        movieNameList = new JList<String>(movieNames);
        movieNameList.addListSelectionListener(new MovieSelectionHandler());
        JScrollPane movieNameListPanel = new JScrollPane(movieNameList);
        JPanel movieNameListContainer = new JPanel(new GridLayout(1,1));
        movieNameListContainer.setBorder(BorderFactory.createTitledBorder("Movies"));
        movieNameListContainer.add(movieNameListPanel);
        
        showtimeList = new JList<String>();
        showtimeList.addListSelectionListener(new ShowtimeSelectionHandler());
        JScrollPane showtimeListPanel = new JScrollPane(showtimeList);
        JPanel showtimeListContainer = new JPanel(new GridLayout(1,1));
        showtimeListContainer.setBorder(BorderFactory.createTitledBorder("Showtimes"));
        showtimeListContainer.add(showtimeListPanel);
        
        seatList = new JList<String>();
        seatList.addListSelectionListener(new SeatSelectionHandler());
        JScrollPane seatListPanel = new JScrollPane(seatList);
        JPanel seatListContainer = new JPanel(new GridLayout(1,1));
        seatListContainer.setBorder(BorderFactory.createTitledBorder("Seats"));
        seatListContainer.add(seatListPanel);
        
        output = new JTextArea(1, 10);
        output.setEditable(false);
        JScrollPane outputPane = new JScrollPane(output,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        createTicketButton = new JButton("Add To Cart");
        createTicketButton.setEnabled(false);
        
        JPanel topHalf = new JPanel();
        topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
		topHalf.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
        topHalf.add(movieNameListContainer);
        topHalf.add(showtimeListContainer);
        topHalf.add(seatListContainer);
        topHalf.setMinimumSize(new Dimension(100, 50));
        
        JPanel bottomHalf = new JPanel(new BorderLayout());
        bottomHalf.add(outputPane, BorderLayout.CENTER);
        bottomHalf.add(createTicketButton, BorderLayout.PAGE_END);
        bottomHalf.setPreferredSize(new Dimension(450, 150));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(topHalf);
        splitPane.add(bottomHalf);
        add(splitPane, BorderLayout.CENTER);
        pack();
        setVisible(true);
	}
	 
    class MovieSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
				createTicketButton.setEnabled(false);
				
				String[] newShowtimeListData = null;
				
				String movieName = movieNameList.getSelectedValue();
				for(Movie m : movies) {
					if(m.getName().equals(movieName)) {
						selectedMovie = m;
						break;
					}
				}

				newShowtimeListData = new String[selectedMovie.getShowTime().size()];
				int i = 0;
				for(Showtime s : selectedMovie.getShowTime()) {
					newShowtimeListData[i] = s.getTime().format(DateTimeFormatter.ofPattern("h:mm a"));
					i++;
				}
				
				showtimeList.setListData(newShowtimeListData);
				seatList.setModel(new DefaultListModel<String>());
				
				output.setText(null);
				output.append("Name: " +selectedMovie.getName() +"\n");
				output.append("Release Date: " +selectedMovie.getReleaseDate().toLocalDate() +"\n");
				output.append("Genre: " +selectedMovie.getGenre() +"\n");
				output.append("Synopsis: " +selectedMovie.getSynopsis() +"\n");
			}
		}
	}
    
    class ShowtimeSelectionHandler implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
				if(showtimeList.getSelectedValue() == null) {
					return;
				}
				
				createTicketButton.setEnabled(false);
				
				String[] newSeatListData = null;
				
				String pickedShowtime = showtimeList.getSelectedValue();
				for(Showtime s : selectedMovie.getShowTime()){
					if(s.getTime().toString().equals(pickedShowtime)){
						selectedShowtime = s;
						break;
					}
				}
				
				newSeatListData = new String[selectedShowtime.getSeatList().size()];
				int i = 0;
				for(Seat s : selectedShowtime.getSeatList()) {
					newSeatListData[i] = s.getID();
					i++;
				}

				seatList.setListData(newSeatListData);
			}
		}
    }
  
    class SeatSelectionHandler implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
				if(seatList.getSelectedValue() == null) {
					return;
				}
				createTicketButton.setEnabled(true);
			}
		}
    }
 
    public void addCreateTicketListener(ActionListener a) { createTicketButton.addActionListener(a); }
    
	public ArrayList<Movie> getMovies() { return movies; }
	public Movie getSelectedMovie() { return selectedMovie; }
	public Showtime getSelectedShowtime() { return selectedShowtime; }
	public JList<String> getMovieNameList() { return movieNameList; }
	public JList<String> getShowtimeList() { return showtimeList; }
	public JList<String> getSeatList() { return seatList; }
	public JTextArea getOutput() { return output; }
	public JButton getCreateTicketButton() { return createTicketButton; }
	
	public void setMovies(ArrayList<Movie> database) { movies = database; }
	public void setSelectedMovie(Movie selectedMovie) { this.selectedMovie = selectedMovie; }
	public void setSelectedShowtime(Showtime selectedShowtime) { this.selectedShowtime = selectedShowtime; }
}




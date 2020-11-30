package View;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

import Model.Movie;
import Model.Showtime;

public class MoviesGUI extends JFrame{
	
	private Movie[] movies;
	private Movie selectedMovie;
	private Showtime selectedShowtime;
	
	private JList<String> movieNameList;
	private JList<String> showtimeList;
	private JList<String> seatList;
	private JTextArea output;
	private JButton createTicketButton;
	
	public MoviesGUI() {
		super("Catalogue");
		
		moviesInitialize(); //TODO Replace this line with a database retriever
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        String[] movieNames = new String[movies.length];
        for(int i = 0; i < movies.length; i++) {
        	movieNames[i] = movies[i].name;
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
        
        createTicketButton = new JButton("Create Ticket");
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
				
				for(int i = 0; i < movies.length; i++) {
					if(movies[i].name.equals(movieNameList.getSelectedValue())){
						selectedMovie = movies[i];
						break;
					}
				}

				newShowtimeListData = new String[selectedMovie.showtimes.size()];
				for(int j = 0; j < selectedMovie.showtimes.size(); j++) {
					newShowtimeListData[j] = selectedMovie.showtimes.get(j).time;
				}
				showtimeList.setListData(newShowtimeListData);
				seatList.setModel(new DefaultListModel<String>());
				
				output.setText(null);
				output.append("Name: " +selectedMovie.name +"\n");
				output.append("Release Date: " +selectedMovie.releaseDate +"\n");
				output.append("Genre: " +selectedMovie.genre +"\n");
				output.append("Synopsis: " +selectedMovie.synopsis +"\n");
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
				for(int i = 0; i < selectedMovie.showtimes.size(); i++) {
					if(selectedMovie.showtimes.get(i).time.equals(showtimeList.getSelectedValue())) {
						selectedShowtime = selectedMovie.showtimes.get(i);
						break;
					}
				}
				
				newSeatListData = new String[selectedShowtime.seats.size()];
				for(int j = 0; j < selectedShowtime.seats.size(); j++) {
					newSeatListData[j] = selectedShowtime.seats.get(j).id;
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
    
	//TODO: Remove this function
	void moviesInitialize() {
		
		String[] movieDatabase = { "Blade Runner 2077", "Arrival", "Ex Machina", "Annihilation" };
		String[] dateDatabase = { "01/01/1999", "02/02/2000", "03/03/2001", "04/04/2002" };
		String[] genreDatabase = { "Sci-Fi", "Action", "Thriller", "Horror" };
		String[][] showtimesDatabase = {{"1:00 PM", "2:00 PM", "3:00 PM"}, 
										{"4:00 PM", "5:00 PM", "6:00 PM"}, 
										{"7:00 PM", "8:00 PM", "9:00 PM"},
										{"10:00 PM", "11:00 PM", "12:00 AM"}};
		String[] seatDatabase = { "A1", "B2", "C3", "D4", "E5", "F6", "G7", "H8", "I9" };

		movies = new Movie[4];
		for(int i = 0; i < 4; i++) {
			movies[i] = new Movie();
			movies[i].initMovie(movieDatabase[i], dateDatabase[i], genreDatabase[i], "Synopsis here??!!!!!!!", showtimesDatabase[i], seatDatabase);
		}
		
	}

	public Movie[] getMovies() { return movies; }
	public Movie getSelectedMovie() { return selectedMovie; }
	public Showtime getSelectedShowtime() { return selectedShowtime; }
	public JList<String> getMovieNameList() { return movieNameList; }
	public JList<String> getShowtimeList() { return showtimeList; }
	public JList<String> getSeatList() { return seatList; }
	public JTextArea getOutput() { return output; }
	public JButton getCreateTicketButton() { return createTicketButton; }
	
	public void setMovies(Movie[] database) { movies = database; }
	public void setSelectedMovie(Movie selectedMovie) { this.selectedMovie = selectedMovie; }
	public void setSelectedShowtime(Showtime selectedShowtime) { this.selectedShowtime = selectedShowtime; }
}




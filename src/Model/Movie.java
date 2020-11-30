package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Movie {
    private String name;
    private LocalDateTime releaseDate;

    private String genre;

    private String synopsis;
    private ArrayList<Showtime> showTimes;

    public Movie(String name, LocalDateTime releaseDate, String genre, String synopsis) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public ArrayList<Showtime> getShowTime() {
        if (LocalDateTime.now().isBefore(releaseDate))
            return null;
        return showTimes;
    }

    public void setShowtime(ArrayList<Showtime> showTimes) {
        this.showTimes = showTimes;
    }


    public boolean forMembers(boolean isRegisterdUser) {
        if (LocalDateTime.now().isBefore(releaseDate) && isRegisterdUser)
            return true;
        return false;
    }
}
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String title;
    private Date release;
    private String plot;
    private double rating;
    private URL poster;
    private ArrayList<String> genres;
    private ArrayList<String> directors;
    private ArrayList<String> stars;

    public Movie(String title, Date release, String plot,
            double rating, ArrayList<String> genres,
            ArrayList<String> directors, ArrayList<String> stars) 
    {
        this.title = title;
        this.release = release;
        this.plot = plot;
        this.rating = rating;
        this.genres = genres;
        this.directors = directors;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public URL getPoster() {
        return poster;
    }

    public void setPoster(URL poster) {
        this.poster = poster;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getStars() {
        return stars;
    }

    public void setStars(ArrayList<String> stars) {
        this.stars = stars;
    }

}

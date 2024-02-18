package models;

import java.lang.invoke.MutableCallSite;

public class Movie {
    private int movie_id;
    private String title;
    private String release_date;
    private String genre;

    public Movie(){

    }

    public Movie(int movie_id, String title, String release_date, String genre){
        this.movie_id = movie_id;
        this.title = title;
        this.release_date = release_date;
        this.genre = genre;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getRelease_date(){
        return release_date;
    }
    public void setRelease_date(String release_date){
        this.release_date = release_date;
    }

    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }


    @Override
    public String toString(){
        return movie_id + " title: " + title + " genre: " + genre + " date: " +release_date;
    }

}

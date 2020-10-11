package by.itacademy.gomel.model;

import java.sql.Date;

public class Movie {

    private int idMovie;
    private String name;
    private Date date;

    public Movie(int idMovie) {
        this.idMovie = idMovie;
    }

    public Movie(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Movie(int idMovie, String name, Date date) {
        this.idMovie = idMovie;
        this.name = name;
        this.date = date;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return " '" + name +
                "'   Screening date: " + date +
                " (ID: " + idMovie + ")"
                ;
    }
}

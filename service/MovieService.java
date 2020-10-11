package by.itacademy.gomel.service;

import by.itacademy.gomel.model.Movie;

import java.sql.SQLException;
import java.sql.Date;

public class MovieService {

    public static Movie addMovie(String name, Date date) throws SQLException, IllegalArgumentException {
        Movie movie = new Movie(name, date);
        return JDBCService.addMovie(movie);
    }

    public static Movie deleteMovie(int idMovie) throws SQLException {
        Movie movie = new Movie(idMovie);
        return JDBCService.deleteMovie(movie);
    }
}

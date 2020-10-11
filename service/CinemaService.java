package by.itacademy.gomel.service;

import by.itacademy.gomel.model.*;

import java.sql.SQLException;
import java.util.List;

public class CinemaService {

    public static List<User> getAllUsers(Cinema cinema) {
        updateUsers(cinema);
        return cinema.getUsers();
    }

    public static void updateUsers(Cinema cinema) {
        List<User> users = null;
        try {
            users = JDBCService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cinema.setUsers(users);
    }

    public static List<Movie> getAllMovies(Cinema cinema) {
        updateMovies(cinema);
        return cinema.getMovies();
    }

    public static void updateMovies(Cinema cinema) {
        List<Movie> movies = null;
        try {
            movies = JDBCService.getAllMovies();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cinema.setMovies(movies);
    }

    public static List<Ticket> getAllTickets(Cinema cinema) {
        updateAllTickets(cinema);
        return cinema.getTickets();
    }

    public static void updateAllTickets(Cinema cinema) {
        List<Ticket> allTickets = null;
        try {
            allTickets = JDBCService.getAllTickets();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cinema.setTickets(allTickets);
    }

    public static List<Ticket> getAllTicketsOnChosenMovie(Cinema cinema) {
        updateTickets(cinema);
        return cinema.getTickets();
    }

    public static void updateTickets(Cinema cinema) {
        List<Ticket> tickets = null;
        try {
            tickets = JDBCService.getAllTicketsOnChosenMovie();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cinema.setTickets(tickets);
    }

    public static List<Order> getAllOrders(Cinema cinema) {
        updateOrders(cinema);
        return cinema.getOrders();
    }

    public static void updateOrders(Cinema cinema) {
        List<Order> orders = null;
        try {
            orders = JDBCService.getAllOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cinema.setOrders(orders);
    }
}
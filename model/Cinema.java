package by.itacademy.gomel.model;

import java.util.List;

public class Cinema {

    private List<User> users;
    private List<Movie> movies;
    private List<Ticket> tickets;
    private List<Order> orders;

    public Cinema() {
    }

    public Cinema(List<User> users, List<Movie> movies, List<Ticket> tickets, List<Order> orders) {
        this.users = users;
        this.movies = movies;
        this.tickets = tickets;
        this.orders = orders;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


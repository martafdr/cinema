package by.itacademy.gomel.service;

import by.itacademy.gomel.model.*;
import by.itacademy.gomel.util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class JDBCService {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<User> users = getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        List<Movie> movies = getAllMovies();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }

        List<Ticket> allTickets = getAllTickets();
        for (Ticket ticket : allTickets) {
            System.out.println(ticket.toString());
        }

        List<Ticket> tickets = getAllTicketsOnChosenMovie();
        for (Ticket ticket : tickets) {
            System.out.println(ticket.toString());
        }

        List<Order> orders = getAllOrders();
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }

    public static List<User> getAllUsers() throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "SELECT * FROM users");
        ResultSet resultSet = dbHelper.openResultSet(preparedStatement);

        int idUser = 0;
        String login = "";
        String password = "";
        TypeUser typeUser = null;

        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            idUser = resultSet.getInt("idUser");
            login = resultSet.getString("login");
            password = resultSet.getString("password");
            typeUser = TypeUser.valueOf(resultSet.getString("typeUser").toUpperCase());
            users.add(new User(idUser, login, password, typeUser));
        }

        dbHelper.closeResultSet(resultSet);
        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);

        return users;
    }

    public static User addRegularUser(User user) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "INSERT INTO users VALUES (?, ?, ?, ?);");

        preparedStatement.setInt(1, user.getIdUser());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, String.valueOf(TypeUser.REGULAR));
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return user;
    }

    public static User addManager(User user) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "INSERT INTO users VALUES (?, ?, ?, ?);");

        preparedStatement.setInt(1, user.getIdUser());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, String.valueOf(TypeUser.MANAGER));
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return user;
    }

    public static User deleteUser(User user) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "DELETE FROM users WHERE idUser=?");
        preparedStatement.setInt(1, user.getIdUser());
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return user;
    }

    public static List<Movie> getAllMovies() throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper
                .openStatement(connection, "SELECT * FROM movies");
        ResultSet resultSet = dbHelper.openResultSet(preparedStatement);

        int idMovie = 0;
        String name = "";
        Date date = Date.valueOf("2019-12-24");

        List<Movie> movies = new ArrayList<>();

        while (resultSet.next()) {
            idMovie = resultSet.getInt("idMovie");
            name = resultSet.getString("name");
            date = resultSet.getDate("date");
            movies.add(new Movie(idMovie, name, date));
        }

        dbHelper.closeResultSet(resultSet);
        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);

        return movies;
    }

    public static Movie addMovie(Movie movie) throws SQLException, IllegalArgumentException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "INSERT INTO movies VALUES (?, ?, ?);");

        preparedStatement.setInt(1, movie.getIdMovie());
        preparedStatement.setString(2, movie.getName());
        preparedStatement.setDate(3, movie.getDate());
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return movie;
    }

    public static Movie deleteMovie(Movie movie) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "DELETE FROM movies WHERE idMovie=?");
        preparedStatement.setInt(1, movie.getIdMovie());
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return movie;
    }

    public static Ticket addTicket(Ticket ticket, int movie) throws SQLException, IllegalArgumentException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "INSERT INTO tickets VALUES (?, ?, ?);");

        preparedStatement.setInt(1, ticket.getIdTicket());
        preparedStatement.setInt(2, 1);
        preparedStatement.setInt(3, movie);
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return ticket;
    }

    public static Ticket changeTicket(Ticket ticket) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "UPDATE tickets SET isAvailable=0 WHERE idTicket=?");
        preparedStatement.setInt(1, ticket.getIdTicket());
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return ticket;
    }

    public static Ticket changeTicketOnAvailable(Ticket ticket) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "UPDATE tickets SET isAvailable=1 WHERE idTicket=?");
        preparedStatement.setInt(1, ticket.getIdTicket());
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return ticket;
    }


    public static List<Ticket> getAllTickets() throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper
                .openStatement(connection, "SELECT * FROM tickets");
        ResultSet resultSet = dbHelper.openResultSet(preparedStatement);

        int idTicket = 0;
        boolean isAvailable = false;
        int movie = 0;

        List<Ticket> allTickets = new ArrayList<>();

        while (resultSet.next()) {
            idTicket = resultSet.getInt("idTicket");
            isAvailable = resultSet.getBoolean("isAvailable");
            movie = resultSet.getInt("movie");
            allTickets.add(new Ticket(idTicket, isAvailable));
        }

        dbHelper.closeResultSet(resultSet);
        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);

        return allTickets;
    }

    public static List<Ticket> getAllTicketsOnChosenMovie() throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        System.out.println("Please enter the idMovie:");
        Scanner scanner = new Scanner(System.in);
        int movie = Integer.parseInt(scanner.nextLine());

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper
                .openStatement(connection, "SELECT * FROM tickets WHERE movie=" + movie);
        ResultSet resultSet = dbHelper.openResultSet(preparedStatement);

        int idTicket = 0;
        boolean isAvailable = false;

        List<Ticket> tickets = new ArrayList<>();

        while (resultSet.next()) {
            idTicket = resultSet.getInt("idTicket");
            isAvailable = resultSet.getBoolean("isAvailable");
            tickets.add(new Ticket(idTicket, isAvailable));
        }

        dbHelper.closeResultSet(resultSet);
        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return tickets;
    }

    public static List<Order> getAllOrders() throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper
                .openStatement(connection, "SELECT * FROM orders");
        ResultSet resultSet = dbHelper.openResultSet(preparedStatement);

        int idOrder = 0;
        int ticket = 0;
        int user = 0;


        List<Order> orders = new ArrayList<>();

        while (resultSet.next()) {
            idOrder = resultSet.getInt("idOrder");
            ticket = resultSet.getInt("ticket");
            user = resultSet.getInt("user");
            orders.add(new Order(idOrder, ticket, user));
        }

        dbHelper.closeResultSet(resultSet);
        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);

        return orders;
    }

    public static Order addOrder(Order order, User user) throws SQLException, IllegalArgumentException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "INSERT INTO orders VALUES (?, ?, ?);");

        preparedStatement.setInt(1, order.getIdOrder());
        preparedStatement.setInt(2, order.getTicket());
        preparedStatement.setInt(3, user.getIdUser());

        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return order;
    }

    public static Order deleteOrder(Order order, User user) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper.openStatement(connection, "DELETE FROM orders WHERE ticket=?");
        preparedStatement.setInt(1, order.getTicket());
        preparedStatement.execute();

        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        return order;
    }
}

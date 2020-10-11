package by.itacademy.gomel.view;

import by.itacademy.gomel.model.Order;
import by.itacademy.gomel.model.Ticket;
import by.itacademy.gomel.model.User;
import by.itacademy.gomel.service.CinemaService;
import by.itacademy.gomel.service.OrderService;
import by.itacademy.gomel.service.TicketService;
import by.itacademy.gomel.util.DBHelper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.itacademy.gomel.service.LogService.addLoggedAction;
import static by.itacademy.gomel.view.AdminConsole.watchAllMovies;
import static by.itacademy.gomel.Runner.cinema;
import static by.itacademy.gomel.Runner.mainMenu;

public class RegularUserConsole {

    public static void regularUserMenu(User user) {
        addLoggedAction("User " + user.getLogin() + " opened regular user menu. \n", user);

        System.out.println("User, select an option:");
        System.out.println("1 - watch all movies;  " +
                "2 - watch all tickets on chosen movie;  " +
                "3 - to order ticket (PS You need ID of Ticket);  \n" +
                "4 - watch my order(s);  " +
                "5 - to return ticket (PS You need ID of Ticket);  " +
                "6 - return to the main menu.");

        Scanner scanner = new Scanner(System.in);

        try {
            switch (Integer.parseInt(scanner.next())) {
                case 1: {
                    watchAllMovies(CinemaService.getAllMovies(cinema));
                    break;
                }
                case 2: {
                    watchAllTicketsOnSelectedMovie(CinemaService.getAllTicketsOnChosenMovie(cinema));
                    break;
                }
                case 3: {
                    addOrder(user);
                    break;
                }
                case 4: {
                    watchUserOrders(user);
                    break;
                }
                case 5: {
                    deleteOrder(user);
                    break;
                }
                case 6: {
                    mainMenu();
                    break;
                }
                default: {
                    System.out.println("No such option!");
                    regularUserMenu(user);
                }
            }
            regularUserMenu(user);
        } catch (NumberFormatException | IOException | InterruptedException | SQLException e) {
            System.out.print("Incorrect data entry!  ");
            regularUserMenu(user);
        }
    }

    public static List<Order> watchUserOrders(User user) throws SQLException {
        DBHelper dbHelper = DBHelper.getInstance();

        Connection connection = dbHelper.openConnection();
        PreparedStatement preparedStatement = dbHelper
                .openStatement(connection, "SELECT * FROM orders WHERE user=" + user.getIdUser());
        ResultSet resultSet = dbHelper.openResultSet(preparedStatement);

        int idOrder = 0;
        int ticket = 0;
        int userId = 0;

        List<Order> ordersOfUser = new ArrayList<>();

        while (resultSet.next()) {
            idOrder = resultSet.getInt("idOrder");
            ticket = resultSet.getInt("ticket");
            userId = resultSet.getInt("user");
            ordersOfUser.add(new Order(idOrder, ticket, userId));
        }

        dbHelper.closeResultSet(resultSet);
        dbHelper.closeStatement(preparedStatement);
        dbHelper.closeConnection(connection);
        System.out.println(ordersOfUser);
        addLoggedAction("User " + user.getLogin() + " watch its orders  \n", user);
        return ordersOfUser;
    }

    public static void watchAllTicketsOnSelectedMovie(List<Ticket> tickets) {

        System.out.println("All tickets on this movie:");
        for (Ticket ticket : tickets) {
            System.out.println(ticket.toString());
        }
    }

    public static void addOrder(User user) throws IOException, InterruptedException, IllegalArgumentException {

        System.out.println("Please enter ID of ticket:");
        Scanner scanner = new Scanner(System.in);
        int ticket = Integer.parseInt(scanner.nextLine());

        Order order = null;
        Ticket ticket1 = null;
        try {
            order = OrderService.addOrder(ticket, user);
            ticket1 = TicketService.changeTicket(ticket);
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("This ticket is not available. Please choose another one!");
            regularUserMenu(user);
        } catch (SQLException e) {
            System.out.println("Try again please!");
            regularUserMenu(user);
        }
        System.out.println("Thank you for your order! You order Ticket: " + ticket);
        addLoggedAction("User " + user.getLogin() + " order ticket "+ ticket+" \n", user);
        regularUserMenu(user);
    }

    public static void deleteOrder(User user) throws IOException, InterruptedException {

        System.out.println("Please enter ID of ticket, which you want return:");
        Scanner scanner = new Scanner(System.in);
        int ticket = Integer.parseInt(scanner.nextLine());

        Order order1 = null;
        Ticket ticket1 = null;
        try {
            order1 = OrderService.deleteOrder(ticket, user);
            ticket1 = TicketService.changeTicketOnAvailable(ticket);

        } catch (SQLIntegrityConstraintViolationException ex) {
            ex.printStackTrace();
            System.out.println("This ticket is not your. Please choose another one!");
            regularUserMenu(user);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Try again please!");
            regularUserMenu(user);
        } System.out.println("Thank you for return ticket!");
        addLoggedAction("User " + user.getLogin() + " return ticket "+ ticket+" \n", user);
        regularUserMenu(user);
    }
}

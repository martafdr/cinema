package by.itacademy.gomel.view;

import by.itacademy.gomel.model.*;
import by.itacademy.gomel.service.CinemaService;
import by.itacademy.gomel.service.MovieService;
import by.itacademy.gomel.service.TicketService;
import by.itacademy.gomel.service.UserService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

import static by.itacademy.gomel.service.LogService.addLoggedAction;
import static by.itacademy.gomel.Runner.mainMenu;

public class AdminConsole {

    private static Cinema cinema = new Cinema();

    public static void adminMenu(User user) {
        addLoggedAction("User " + user.getLogin() + " opened admin menu. \n", user);

        System.out.println("Admin, select an option:  " +
                "1 - watch all movies;  " +
                "2 - add movie (don't remember add tickets);  " +
                "3 - add tickets (PS you need id of movie);  \n" +
                "4 - delete movie (PS you need id of movie);  " +
                "5 - watch all tickets;  " +
                "6 - watch all orders;  " +
                "7 - watch all users;  " +
                "8 - add new manager;  \n" +
                "9 - delete user (PS you need id of user);  " +
                "10 - return to the main menu.  ");

        Scanner scanner = new Scanner(System.in);

        try {
            switch (Integer.parseInt(scanner.next())) {
                case 1: {
                    watchAllMovies(CinemaService.getAllMovies(cinema));
                    break;
                }
                case 2: {
                    addMovie(user);
                    break;
                }
                case 3: {
                    addTicket(user);
                    break;
                }
                case 4: {
                    deleteMovie(user);
                    break;
                }
                case 5: {
                    watchAllTickets(CinemaService.getAllTickets(cinema));
                    break;
                }
                case 6: {
                    watchAllOrders(CinemaService.getAllOrders(cinema));
                    break;
                }
                case 7: {
                    watchAllUsers(CinemaService.getAllUsers(cinema));
                    break;
                }
                case 8: {
                    registerManager(user);
                    break;
                }
                case 9: {
                    deleteUser(user);
                    break;
                }
                case 10: {
                    mainMenu();
                    break;
                }

                default: {
                    System.out.println("No such option!");
                    adminMenu(user);
                }
            }
            adminMenu(user);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.out.print("Incorrect data entry!  ");
            adminMenu(user);
        }
    }

    public static void watchAllMovies(List<Movie> movies) {

        System.out.println("\t Available movies in our cinema:");
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    public static void addMovie(User user) throws IOException, InterruptedException, IllegalArgumentException {

        System.out.println("Please enter name of movie:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Please enter screening date in format YYYY-MM-DD:");
        Date date = Date.valueOf(scanner.nextLine());

        Movie movie = null;
        try {
            movie = MovieService.addMovie(name, date);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Try again please!");
            adminMenu(user);
        }
        addLoggedAction("User " + user.getLogin() + " add movie. \n", user);
        System.out.println("Movie added successfully! ");
        adminMenu(user);
    }

    public static void addTicket(User user) throws IOException, InterruptedException, IllegalArgumentException {

        System.out.println("Please enter id of movie, on which necessary add tickets:");
        Scanner scanner = new Scanner(System.in);
        int movie = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter quantity of necessary tickets:");
        int quantity = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= quantity; i++) {
            Ticket ticket = null;
            try {
                ticket = TicketService.addTicket(movie);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Try again please!");
                adminMenu(user);
            }
        }
        addLoggedAction("User " + user.getLogin() + " add ticket(s). \n", user);
        System.out.println("Ticket(s) added successfully!");
        adminMenu(user);
    }

    public static void deleteMovie(User user) throws IOException, InterruptedException {

        System.out.println("Please enter movie's id, which you want to delete:");
        Scanner scanner = new Scanner(System.in);
        int idMovie = Integer.parseInt(scanner.nextLine());

        Movie movie = null;
        try {
            movie = MovieService.deleteMovie(idMovie);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Movie with this id not found. Try again please!");
            adminMenu(user);
        }
        System.out.println("Movie delete successfully!");
        addLoggedAction("User " + user.getLogin() + " delete movie. \n", user);
        adminMenu(user);
    }

    public static void watchAllTickets(List<Ticket> allTickets) {

        System.out.println("All tickets:");
        for (Ticket ticket : allTickets) {
            System.out.println(ticket.toString());
        }
    }

    public static void watchAllOrders(List<Order> orders) {

        System.out.println("All orders:");
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }

    public static void watchAllUsers(List<User> users) {

        System.out.println("All users:");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public static void registerManager(User user) throws IOException, InterruptedException {

        System.out.println("Please enter manager's login:");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println("Please enter manager's password:");
        String password = scanner.nextLine();

        User user1 = null;
        try {
            user1 = UserService.registerManager(login, password);
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("This login is not available. Please choose another one!");
            adminMenu(user);
        } catch (SQLException e) {
            System.out.println("Try again please!");
            adminMenu(user);
        }
        addLoggedAction("User " + user.getLogin() + " add new manager" + login + ". \n", user);
        System.out.println("Manager added successfully!");
        adminMenu(user);
    }

    public static void deleteUser(User user) throws IOException, InterruptedException {

        System.out.println("Please enter user's id, which you want to delete:");
        Scanner scanner = new Scanner(System.in);
        int idUser = Integer.parseInt(scanner.nextLine());

        User user1 = null;
        try {
            if (idUser == 1) {
                System.out.println("You can not delete yourself!!!");
                adminMenu(user);
            } else user1 = UserService.deleteUser(idUser);
        } catch (SQLException e) {
            System.out.println("User with this id not found. Try again please!");
            adminMenu(user);
        }
        System.out.println("User delete successfully!");
        addLoggedAction("User " + user.getLogin() + " delete user. \n", user);
        adminMenu(user);
    }
}

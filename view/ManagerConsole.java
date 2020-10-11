package by.itacademy.gomel.view;

import by.itacademy.gomel.model.Cinema;
import by.itacademy.gomel.model.User;
import by.itacademy.gomel.service.CinemaService;

import java.io.IOException;
import java.util.Scanner;

import static by.itacademy.gomel.service.LogService.addLoggedAction;
import static by.itacademy.gomel.view.AdminConsole.*;
import static by.itacademy.gomel.Runner.mainMenu;
import static by.itacademy.gomel.view.RegularUserConsole.watchAllTicketsOnSelectedMovie;

public class ManagerConsole {
    private static Cinema cinema = new Cinema();

    public static void managerMenu(User user) {
        addLoggedAction("User " + user.getLogin() + " opened manager menu. \n", user);
        System.out.println("Manager, select an option:  " +
                "1 - watch all movies;  " +
                "2 - add movie (don't remember add tickets);  " +
                "3 - add tickets (PS you need id of movie);  " +
                "4 - delete movie (PS you need id of movie);  \n" +
                "5 - watch all tickets on selected movie;  " +
                "6 - watch all users;  " +
                "7 - return to the main menu.");

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
                    watchAllTicketsOnSelectedMovie(CinemaService.getAllTicketsOnChosenMovie(cinema));
                    break;
                }
                case 6: {
                    watchAllUsers(CinemaService.getAllUsers(cinema));
                    break;
                }
                case 7: {
                    mainMenu();
                    break;
                }
                default: {
                    System.out.println("No such option!");
                    managerMenu(user);
                }
            }
            managerMenu(user);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.out.print("Incorrect data entry!  ");
            managerMenu(user);
        }
    }
}

package by.itacademy.gomel;

import by.itacademy.gomel.model.Cinema;
import by.itacademy.gomel.model.TypeUser;
import by.itacademy.gomel.model.User;
import by.itacademy.gomel.service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static by.itacademy.gomel.view.AdminConsole.adminMenu;
import static by.itacademy.gomel.view.ManagerConsole.managerMenu;
import static by.itacademy.gomel.view.RegularUserConsole.regularUserMenu;

public class Runner {

    public static Cinema cinema;

    public static void main(String[] args) {
        cinema = new Cinema();
        System.out.print("Hello User!  ");
        mainMenu();
    }

    public static void mainMenu() {

        System.out.println("Please, enter 1 for login, 2 for registration:");

        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextInt()) {
                case 1: {
                    loginMenu();
                    break;
                }
                case 2: {
                    registerRegularUserMenu();
                    break;
                }
                default:
                    System.out.print("Choose one of two variant!  ");
                    mainMenu();
            }
        } catch (IOException | InterruptedException | InputMismatchException e) {
            System.out.print("Incorrect data entry!  ");
            mainMenu();
        }
    }

    public static void loginMenu() throws IOException, InterruptedException {

        System.out.println("Please enter your login:");

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        User user = UserService.loginUser(login, password);
        if (user != null) {
            if (user.getTypeUser().equals(TypeUser.ADMIN)) {
                adminMenu(user);
            } else if (user.getTypeUser().equals(TypeUser.MANAGER)) {
                managerMenu(user);
            }
            regularUserMenu(user);
        } else {
            System.out.println("Please check your login|password!");
            mainMenu();
        }
    }

    public static void registerRegularUserMenu() throws IOException, InterruptedException {

        System.out.println("Please enter your login:");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        User user = null;
        try {
            user = UserService.registerRegularUser(login, password);
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("This login is not available. Please choose another one!");
            mainMenu();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Try again please!");
            mainMenu();
        }

        System.out.println("User added successfully!");
        regularUserMenu(user);
    }
}


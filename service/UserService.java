package by.itacademy.gomel.service;


import by.itacademy.gomel.model.User;

import java.sql.SQLException;
import java.util.List;


public class UserService {

    public static User loginUser(String login, String password) {

        User user = null;

        try {
            List<User> users = JDBCService.getAllUsers();

            for (User tempUser : users) {
                if (tempUser.getLogin().equals(login)) {
                    if (tempUser.getPassword().equals(password)) {
                        user = tempUser;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User registerRegularUser(String login, String password) throws SQLException {
        User user = new User(login, password);
        return JDBCService.addRegularUser(user);
    }

    public static User registerManager(String login, String password) throws SQLException {
        User user = new User(login, password);
        return JDBCService.addManager(user);
    }

    public static User deleteUser(int idUser) throws SQLException {
        User user = new User(idUser);
        return JDBCService.deleteUser(user);
    }
}

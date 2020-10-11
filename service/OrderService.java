package by.itacademy.gomel.service;

import by.itacademy.gomel.model.Order;
import by.itacademy.gomel.model.User;

import java.sql.SQLException;


public class OrderService {

    public static Order addOrder(int ticket, User user) throws SQLException, IllegalArgumentException {
        Order order = new Order(ticket);
        return JDBCService.addOrder(order, user);
    }
    public static Order deleteOrder(int idOrder, User user) throws SQLException {
        Order order = new Order(idOrder);
        return JDBCService.deleteOrder(order, user);
    }
}

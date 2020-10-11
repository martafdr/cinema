package by.itacademy.gomel.service;

import by.itacademy.gomel.model.Ticket;

import java.sql.SQLException;

public class TicketService {

    public static Ticket addTicket(int movie) throws SQLException, IllegalArgumentException {
        Ticket ticket = new Ticket();
        return JDBCService.addTicket(ticket, movie);
    }

    public static Ticket changeTicket(int idTicket) throws SQLException {
        Ticket ticket = new Ticket(idTicket);
        return JDBCService.changeTicket(ticket);
    }

    public static Ticket changeTicketOnAvailable(int idTicket) throws SQLException {
        Ticket ticket = new Ticket(idTicket);
        return JDBCService.changeTicketOnAvailable(ticket);
    }
}

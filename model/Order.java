package by.itacademy.gomel.model;

public class Order {
    private int idOrder;
    private int ticket;
    private int user;

    public Order(int ticket) {
        this.ticket = ticket;
    }

    public Order(int idOrder, int ticket) {
        this.idOrder = idOrder;
        this.ticket = ticket;
    }

    public Order(int idOrder, int ticket, int user) {
        this.idOrder = idOrder;
        this.ticket = ticket;
        this.user = user;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return
                "Order №" + idOrder +
                        ". ID of Ticket: " + ticket +
                        " (User: " + user + ")";
    }
}

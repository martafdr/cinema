package by.itacademy.gomel.model;

public class Ticket {

    private int idTicket;
    private boolean isAvailable;
    private int movie;

    public Ticket() {
    }

    public Ticket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(int idTicket, boolean isAvailable) {
        this.idTicket = idTicket;
        this.isAvailable = isAvailable;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean availbale) {
        isAvailable = availbale;
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "ID ticket: " + idTicket +
                ". It is available (" + isAvailable + ")";
    }
}

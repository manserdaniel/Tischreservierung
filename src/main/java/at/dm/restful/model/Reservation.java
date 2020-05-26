package at.dm.restful.model;

public class Reservation {
    private int id;
    private String userEmail;
    private int amountSeats;

    public Reservation(String userEmail, int amountSeats) {
        this.userEmail = userEmail;
        this.amountSeats = amountSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getAmountSeats() {
        return amountSeats;
    }

    public void setAmountSeats(int amountSeats) {
        this.amountSeats = amountSeats;
    }
}

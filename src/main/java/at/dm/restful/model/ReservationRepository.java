package at.dm.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements Repository<Reservation> {

    private DatabaseConnector connector;

    public ReservationRepository() {
        this.connector = DatabaseConnector.getInstance();
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        ResultSet result = connector.fetchData("SELECT * FROM reservation");

        if (result == null) {
            System.out.println("No users found!");
            return null;
        }

        try {
            while (result.next()) {
                Reservation reservation = new Reservation(result.getString("user_email"), result.getInt("amount_seats"));
                reservation.setId(result.getInt("id"));
                reservations.add(reservation);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connector.closeConnection();
            return reservations;
        }
    }

    @Override
    public Reservation findOne(int id) {
        Reservation reservation = null;
        ResultSet result = connector.fetchData("SELECT * FROM reservation WHERE id = '" + id + "'");

        if (result == null) {
            System.out.println("No user found!");
            return null;
        }

        try {
            while (result.next()) {
                reservation = new Reservation(result.getString("user_email"), result.getInt("amount_seats"));
                reservation.setId(result.getInt("id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connector.closeConnection();
            return reservation;
        }
    }

    @Override
    public int create(Reservation reservation) {
        return connector.insert("INSERT INTO reservation (user_email, amount_seats) VALUES ('" + reservation.getUserEmail() + "', '" + reservation.getAmountSeats() + "' )");
    }

    public void deleteOne(int id) {
        connector.delete("DELETE FROM reservation WHERE id = '" + id + "'");
    }

    public void update(int id, int amountSeats) {
        connector.update("UPDATE reservation SET amount_seats = '" + amountSeats + "' WHERE id = '" + id + "'");
    }
}

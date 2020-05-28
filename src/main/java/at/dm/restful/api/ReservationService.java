package at.dm.restful.api;

import at.dm.restful.model.Reservation;
import at.dm.restful.model.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepository = new ReservationRepository();
    List<Reservation> reservations = reservationRepository.findAll();

    public void updateList() {
        reservations = reservationRepository.findAll();
    }

    public List<Reservation> getReservationList() {
        return reservations;
    }

    public Reservation getReservation(int id) {
        return reservations.stream().filter(r -> r.getId() == id).findFirst().get();
    }

    public void addReservation(Reservation reservation) {
        reservationRepository.create(reservation);
    }

    public void deleteReservation(int id) {
        reservationRepository.deleteOne(id);
    }

    public void updateReservation(int id, int amountSeats) {
        reservationRepository.update(id, amountSeats);
    }
}

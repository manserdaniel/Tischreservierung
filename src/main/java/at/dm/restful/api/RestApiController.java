package at.dm.restful.api;
import java.util.List;
import java.util.Optional;

import at.dm.restful.model.Reservation;
import at.dm.restful.model.Restaurant;
import at.dm.restful.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RestApiController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/restaurants")
    public List<Restaurant> restaurants() {
        return restaurantService.getRestaurantList();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant restaurant(@PathVariable int id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurants")
    public void addRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        restaurantService.updateList();
    }

    @PutMapping("/restaurants/{id}")
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        // restaurants.add liste adden
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);
        restaurantService.updateList();
    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.getUserList();
    }

    @GetMapping("/users/{id}")
    public User user(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping("/authenticate")
    public Optional<String> checkUser(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/reservations")
    public List<Reservation> reservations() {
        return reservationService.getReservationList();
    }

    @GetMapping("/reservations/{id}")
    public Reservation reservation(@PathVariable int id) {
        return reservationService.getReservation(id);
    }

    @PostMapping("/reservations")
    public ResponseEntity addReservation(@RequestBody Reservation reservation, @RequestHeader(value="Authorization") String token) {
        String bearerToken = token.split(" ")[1];

        if (token == "") {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else if (userService.checkToken(bearerToken)) {
            reservationService.addReservation(reservation);
            reservationService.updateList();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
    }

    @CrossOrigin
    @PutMapping("/reservations/{id}")
    public ResponseEntity changeReservation(@PathVariable int id, @RequestHeader(value="Authorization") String token, @RequestBody Reservation reservation) {
        String bearerToken = token.split(" ")[1];

        if (token == "") {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else if (userService.checkToken(bearerToken)) {
            reservationService.updateReservation(id, reservation.getAmountSeats());
            reservationService.updateList();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        reservationService.updateList();
    }

}
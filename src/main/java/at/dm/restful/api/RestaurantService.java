package at.dm.restful.api;

import at.dm.restful.model.Restaurant;
import at.dm.restful.model.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    RestaurantRepository restaurantRepository = new RestaurantRepository();
    List<Restaurant> restaurants = restaurantRepository.findAll();

    public void updateList() {
        restaurants = restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantList() {
        return restaurants;
    }

    public Restaurant getRestaurant(int id) {
        return restaurants.stream().filter(r -> r.getId() == id).findFirst().get();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.create(restaurant);
    }

    public void deleteRestaurant(int id) {
        restaurantRepository.deleteOne(id);
    }

}

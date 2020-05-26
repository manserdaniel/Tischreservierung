package at.dm.restful;

import at.dm.restful.model.Restaurant;
import at.dm.restful.model.RestaurantRepository;
import at.dm.restful.model.User;
import at.dm.restful.model.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
/*
		UserRepository userRepository = new UserRepository();

		User userOne = new User("userOne@userOne.at", "userOne");
		User userTwo = new User("userTwo@userTwo.at", "userTwo");

		userRepository.create(userOne);
		userRepository.create(userTwo);

		List<User> userList = new ArrayList<>();
		userList = userRepository.findAll();

		RestaurantRepository restaurantRepository = new RestaurantRepository();

		Restaurant restaurant = new Restaurant();
		restaurant.setName("One Restaurant");
		restaurant.setLocation("BRegenz");
		restaurant.setTables(10);

		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList = restaurantRepository.findAll();
		*/

	}
}

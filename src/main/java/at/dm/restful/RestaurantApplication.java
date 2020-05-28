package at.dm.restful;

import at.dm.restful.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		SpringApplication.run(RestaurantApplication.class, args);

	}
}

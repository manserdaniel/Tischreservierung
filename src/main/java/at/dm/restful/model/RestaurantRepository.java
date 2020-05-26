package at.dm.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository implements Repository<Restaurant> {

    private DatabaseConnector connector;

    public RestaurantRepository() {
        this.connector = DatabaseConnector.getInstance();
    }

    @Override
    public List<Restaurant> findAll() {

        List<Restaurant> restaurant = new ArrayList<>();

        ResultSet result = connector.fetchData("SELECT * FROM restaurant");

        if (result == null) {
            System.out.println("No Customers found!");
            return null;
        }

        try {
            while (result.next()) {
                Restaurant tempRestaurant = new Restaurant();
                tempRestaurant.setId(result.getInt("id"));
                tempRestaurant.setName(result.getString("name"));
                tempRestaurant.setLocation(result.getString("location"));
                tempRestaurant.setTables(result.getInt("tables"));
                restaurant.add(tempRestaurant);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connector.closeConnection();
            return restaurant;
        }
    }

    @Override
    public Restaurant findOne(int id) {
        Restaurant restaurant = null;
        ResultSet result = connector.fetchData("SELECT * FROM restaurant WHERE id = '" + id + "'");

        if (result == null) {
            System.out.println("No Customer found!");
            return null;
        }

        try {
            while (result.next()) {
                restaurant = new Restaurant();
                restaurant.setId(result.getInt("id"));
                restaurant.setName(result.getString("name"));
                restaurant.setLocation(result.getString("location"));
                restaurant.setTables(result.getInt("tables"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connector.closeConnection();
            return restaurant;
        }
    }

    @Override
    public int create(Restaurant restaurant) {
        return connector.insert("INSERT INTO restaurant (name, location, tables) VALUES ('" + restaurant.getName() + "', '" + restaurant.getLocation() + "', '" + restaurant.getTables() + "' )");
    }

    public void deleteOne(int id) {
        connector.delete("DELETE FROM restaurant WHERE id = '" + id + "'");
    }
}

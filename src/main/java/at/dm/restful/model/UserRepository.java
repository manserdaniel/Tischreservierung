package at.dm.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private DatabaseConnector connector;

    public UserRepository() {
        this.connector = DatabaseConnector.getInstance();
    }

    @Override
    public List<User> findAll() {
        List<User> user = new ArrayList<>();
        ResultSet result = connector.fetchData("SELECT * FROM user");

        if (result == null) {
            System.out.println("No users found!");
            return null;
        }

        try {
            while (result.next()) {
                User tempUser = new User(result.getString("email"), result.getString("password"));
                tempUser.setId(result.getInt("id"));
                Token token = new Token();
                token.setToken(result.getString("token"));
                tempUser.setToken(token);
                user.add(tempUser);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connector.closeConnection();
            return user;
        }
    }

    @Override
    public User findOne(int id) {
        User user = null;
        ResultSet result = connector.fetchData("SELECT * FROM user WHERE id = '" + id + "'");

        if (result == null) {
            System.out.println("No user found!");
            return null;
        }

        try {
            while (result.next()) {
                user = new User(result.getString("email"), result.getString("password"));
                user.setId(result.getInt("id"));
                Token token = new Token();
                token.setToken(result.getString("token"));
                user.setToken(token);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connector.closeConnection();
            return user;
        }
    }

    @Override
    public int create(User user) {
        return connector.insert("INSERT INTO user (email, password) VALUES ('" + user.getEmail() + "', '" + user.getPassword() + "' )");
    }

    public void deleteOne(int id) {
        connector.delete("DELETE FROM user WHERE id = '" + id + "'");
    }

    public void updateToken(int id, String token) {
        connector.update("UPDATE user SET token = '" + token + "' WHERE id = '" + id + "'");
    }
}

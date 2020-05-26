package at.dm.restful.api;

import at.dm.restful.model.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
public class UserService {
    UserRepository userRepository = new UserRepository();
    List<User> users = userRepository.findAll();

    /*
    RSAPrivateKey privateKey = (RSAPrivateKey) getRSAPrivateKeyFromFile(); //Get the key instance

    public String getRSAPrivateKeyFromFile() {
        String data = null;
        try {
            File myObj = new File("jwtRS256.key");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }*/

    public void updateList() {
        users = userRepository.findAll();
    }

    public List<User> getUserList() {
        return users;
    }

    public User getUser(int id) {
        return users.stream().filter(r -> r.getId() == id).findFirst().get();
    }

    public void addUser(User user) {
        userRepository.create(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteOne(id);
    }

    public Token login(User user) {
        User newUser;
        Token token = new Token();
        Token failed = new Token();
        try {
            newUser = users.stream().filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail())).findFirst().get();
            if (newUser.getPassword().equals(user.getPassword())) {
                /*
                try {
                    Algorithm algorithm = Algorithm.RSA256(null, privateKey);

                    token.setToken(JWT.create()
                            .withIssuer("auth0")
                            .sign(algorithm));

                } catch (JWTCreationException exception){
                    //Invalid Signing configuration / Couldn't convert Claims.
                }*/
                if (user.getToken() == null) {
                    // create new token
                    token.setToken("basjklfbjklsbngklvsnlakdhfvinasdklvbioadrsjgvbmklsdr");
                    userRepository.updateToken(newUser.getId(), token.getToken());

                    return token;
                } else if (newUser.getToken().equals(user.getToken())) {
                    // get token from userdata
                    return user.getToken();
                }
            }
        } catch (NoSuchElementException e) {
            failed.setToken("failed");
        }
        return failed;
    }

    public boolean checkLogin(String email, String token) {
        User user;
        try {
            user = users.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst().get();

            if (user.getToken().getToken().equals(token)) {
                // create new token
                return true;
            } else {
                return false;
            }

        } catch (NoSuchElementException e) {
        }
        return false;
    }
}

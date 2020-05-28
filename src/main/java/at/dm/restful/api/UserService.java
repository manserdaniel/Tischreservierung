package at.dm.restful.api;

import at.dm.restful.model.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository = new UserRepository();
    List<User> users = userRepository.findAll();

    private static final String SECRET_KEY = "some secret";

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

    public Optional<String> login(User user) {
        User newUser;
        String token = "";
        try {
            newUser = users.stream().filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail())).findFirst().get();
            if (newUser.getPassword().equals(user.getPassword())) {
                token = generateJWT(newUser);

            }
        } catch (NoSuchElementException e) {
            return Optional.empty();
        } finally {
            return Optional.of(token);
        }
    }

    public boolean checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(token).getBody();

            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String generateJWT(User user){
        return Jwts.builder().setSubject(user.getEmail()).setSubject(user.getPassword().toString()).signWith(SignatureAlgorithm.HS256, DatatypeConverter.parseBase64Binary(SECRET_KEY)).compact();
    }
}

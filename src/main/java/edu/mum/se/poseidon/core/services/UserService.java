package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.repositories.UserRepository;
import edu.mum.se.poseidon.core.repositories.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * Created by Yuriy Yugay on 10/11/2017.
 *
 * @author Yuriy Yugay
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            return null;
        }
        String pass = user.getPassword();

        if(!pass.equals(password)) {
            return null;
        }

        return user;
    }
}

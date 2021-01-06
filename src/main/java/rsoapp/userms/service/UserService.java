package rsoapp.userms.service;

import org.springframework.stereotype.Service;
import rsoapp.userms.model.entity.User;
import rsoapp.userms.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).get();
    }

    public User getUserByUsername(String email) {
        return userRepository.getUserByEmail(email);
    }
}

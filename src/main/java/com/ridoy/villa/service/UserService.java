package com.ridoy.villa.service;

import com.ridoy.villa.model.User;
import com.ridoy.villa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Create a new User
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing User
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    // Delete a User by ID
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }

    // Basic login validation
    public String validateUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsernameAndIsActive(username, true);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Simple password validation, consider using password encryption like BCrypt
            if (user.getPassword().equals(password)) {
                return user.getRole();
            }
        }
        return null;
    }
}

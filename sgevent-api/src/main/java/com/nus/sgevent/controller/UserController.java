package com.nus.sgevent.controller;

import com.nus.sgevent.model.User;
import com.nus.sgevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // User registration
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // User login is handled by Spring Security configuration in SecurityConfig,
    // so no explicit implementation here.

    // Change password
    @PostMapping("/change-password")
    public String changePassword(@RequestParam Long userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return userRepository.findById(userId).map(user -> {
            // Verify the old password is correct
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                // Encrypt the new password and update
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return "Password changed successfully.";
            } else {
                return "Old password is incorrect.";
            }
        }).orElse("User not found.");
    }

    // Grant higher privileges
    @PostMapping("/{userId}/grant")
    public String grantUserRole(@PathVariable Long userId, @RequestParam String role) {
        return userRepository.findById(userId).map(user -> {
            // Assume a method exists to update the user's role, implement as per actual logic
            // user.setRole(role);
            userRepository.save(user);
            return "User role updated successfully.";
        }).orElse("User not found.");
    }

    // Retrieve all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

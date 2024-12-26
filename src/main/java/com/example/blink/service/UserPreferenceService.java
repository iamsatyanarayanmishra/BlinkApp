package com.example.blink.service;

import com.example.blink.model.User;
import com.example.blink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceService {
    @Autowired
    private UserRepository userRepository;

    public void saveUserPreference(String username, String preference) {
        // Find the user by ID
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
                
        // Update user details
        user.setPreference(preference);

        // Save the updated user to the database
        userRepository.save(user);
    }
}


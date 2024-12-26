package com.example.blink.service;
import com.example.blink.dto.UserRequest;
import com.example.blink.model.User;
import com.example.blink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUserName(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUsername());
        userRepository.save(user);
    }

    public User updateUserDetails(String username, String name, String email) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    
        if (userRepository.existsByEmail(email) && !user.getEmail().equals(email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        user.setName(name);
        user.setEmail(email);
    
        return userRepository.save(user);
    }
    
    public User updateUserContact(String username, String number, String countryCode) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    
        user.setCountryCode(countryCode);
        user.setPhoneNumber(number);
        return userRepository.save(user);
    }

    public void updateUserNumber(String username, String number) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setPhoneNumber(number);
        userRepository.save(user);
    }

    public void updateUserEmail(String username, String email){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setEmail(email);
        userRepository.save(user);
    }

    public void updateUserName(String username, String name){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setName(name);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchUsers(String searchQuery) {
        return userRepository.findByUsernameContainingOrNameContaining(searchQuery, searchQuery);
    }

    public User archiveChat(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setPreference("archived");
        return userRepository.save(user);
    }

    public void deleteChat(long userId) {
        userRepository.deleteById(userId);
    }
}

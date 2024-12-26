package com.example.blink.controller;

import com.example.blink.model.User;
import com.example.blink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private UserService userService;

    // Fetch all users (chats) - You can customize the data to fetch from the User model
    @GetMapping("/{username}")
    public ResponseEntity<List<User>> getChats(@PathVariable String username, @RequestParam(name = "search", required = false) String search) {
        List<User> users = search != null ? userService.searchUsers(search) : userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Archive a chat (update user preferences)
    @PostMapping("/{username}/archive")
    public ResponseEntity<User> archiveChat(@PathVariable String username) {
        User updatedUser = userService.archiveChat(username);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a chat (remove user)
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteChat(@PathVariable long username) {
        userService.deleteChat(username);
        return ResponseEntity.noContent().build();
    }
}

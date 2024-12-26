package com.example.blink.controller;

import com.example.blink.dto.*;
import com.example.blink.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-username")
    public ResponseEntity<String> saveUsername(@RequestBody UserRequest userRequest) {
        userService.saveUserName(userRequest);
        return ResponseEntity.ok("Username saved successfully!");
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody UserDetailsUpdateRequest request) {
        userService.updateUserDetails(request.getUserName(), request.getName(), request.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("name", request.getName());
        response.put("email", request.getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-contact")
    public ResponseEntity<String> updateContact(@RequestBody UserContactRequest request) {
        try {
            userService.updateUserContact(request.getUserName(), request.getPhoneNumber(), request.getCountryCode());
            return ResponseEntity.ok("Contact updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-number")
    public ResponseEntity<Map<String, String>> updateUserNumber(@RequestBody UserNumberUpdateRequest request) {
        try {
            userService.updateUserNumber(request.getUserName(), request.getPhoneNumber());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Number updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/update-email")
    public ResponseEntity<Map<String, String>> updateUserEmail(@RequestBody UserEmailUpdateRequest request) {
        try {
            userService.updateUserEmail(request.getUserName(), request.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email Updated Successfully.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/update-name")
    public ResponseEntity<Map<String, String>> updateUserName(@RequestBody UserNameUpdateRequest request) {
        try {
            userService.updateUserName(request.getUserName(), request.getName());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Name Updated Successfully.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}

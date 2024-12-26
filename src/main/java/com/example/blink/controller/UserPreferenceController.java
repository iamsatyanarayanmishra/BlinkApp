package com.example.blink.controller;

import com.example.blink.dto.UserPreferenceRequest;
import com.example.blink.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @PostMapping("/preferences")
    public ResponseEntity<String> saveUserPreference(@RequestBody UserPreferenceRequest request) {
        userPreferenceService.saveUserPreference(request.getUserName(), request.getPreference());
        System.out.println(request.getUserName());
        return ResponseEntity.ok("Preference Updated Successfully.");
    }
}


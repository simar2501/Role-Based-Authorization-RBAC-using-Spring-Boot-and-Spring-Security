package com.example.experiment7.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public Map<String, String> adminDashboard(Authentication authentication) {
        return Map.of(
            "message", "Welcome, admin!",
            "username", authentication.getName(),
            "role", authentication.getAuthorities().toString()
        );
    }
}
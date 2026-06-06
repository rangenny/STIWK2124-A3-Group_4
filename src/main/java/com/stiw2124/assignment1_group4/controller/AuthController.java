package com.stiw2124.assignment1_group4.controller;

import com.stiw2124.assignment1_group4.model.User;
import com.stiw2124.assignment1_group4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Simple request body holder for the login form
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // POST /api/auth/login  ->  validates username + password against the DB
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> found = userRepository.findByUsername(request.getUsername());

        // matches() compares the plain-text input against the stored BCrypt hash
        if (found.isPresent()
                && passwordEncoder.matches(request.getPassword(), found.get().getPassword())) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "username", found.get().getUsername()
            ));
        }

        // Invalid username or password
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "Invalid username or password"
        ));
    }
}

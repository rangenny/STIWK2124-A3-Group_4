package com.stiw2124.assignment1_group4.controller;

import com.stiw2124.assignment1_group4.model.User;
import com.stiw2124.assignment1_group4.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    // Secure the DTO with validation annotations
    public static class LoginRequest {
        @NotBlank(message = "Username cannot be blank")
        private String username;
        
        @NotBlank(message = "Password cannot be blank")
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // Trigger validation with @Valid
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Optional<User> found = userRepository.findByUsername(request.getUsername());

        if (found.isPresent() && passwordEncoder.matches(request.getPassword(), found.get().getPassword())) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "username", found.get().getUsername()
            ));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "Invalid username or password"
        ));
    }
}
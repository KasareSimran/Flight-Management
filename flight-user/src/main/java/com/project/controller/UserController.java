package com.project.controller;


import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.project.config.JwtProvider;
import com.project.model.User;
import com.project.repository.UserRepo;
import com.project.dto.LoginRequest;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    // Register
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getMobileNumber(),
                        loginRequest.getPassword()
                )
        );
        String jwt = JwtProvider.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    // Get Current User
    @GetMapping("/api/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String mobile = authentication.getName();
        return ResponseEntity.ok(userService.findUserByMobile(mobile));
    }

    // Get All
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get by ID
    @GetMapping("/api/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Throwable {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Update
    @PutMapping("/api/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws Throwable {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // Delete
    @DeleteMapping("/api/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

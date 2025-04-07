package com.project.controller;

import com.project.config.JwtProvider;
import com.project.dto.LoginRequest;
import com.project.model.User;
import com.project.repository.UserRepo;
import com.project.service.CustomerUserDetailService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailService customerUserDetailService;


    // Register
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
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

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails=userService.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}

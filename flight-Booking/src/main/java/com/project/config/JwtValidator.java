package com.project.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    @Autowired
    private JwtProvider jwtProvider;

    public String validateTokenAndGetMobileNumber(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new Exception("Missing or invalid Authorization header");
        }

        if (!jwtProvider.isTokenValid(token)) {
            throw new Exception("Invalid or expired JWT token");
        }

        return jwtProvider.getMobileNumberFromToken(token);
    }
}
//package com.project.config;
//
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import java.util.Date;
//
//@Component
//public class JwtProvider {
//
//    private static final String SECRET = JwtConstant.SECRET_KEY;
//    private static final SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET.getBytes()));
//
//    public String getMobileNumberFromToken(String token) {
//        token = token.substring(7); // remove Bearer
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        return String.valueOf(claims.get("mobileNumber"));
//    }
//
//    public boolean isTokenValid(String token) {
//        try {
//            token = token.substring(7); // remove Bearer
//            Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            System.out.println("JWT validation error: " + e.getMessage());
//            return false;
//        }
//    }
//}

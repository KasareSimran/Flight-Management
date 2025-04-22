package com.project.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtValidator extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtValidator(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/auth") || path.startsWith("/public")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(JwtConstant.HEADER);

        if (token != null && token.startsWith("Bearer ")) {
            System.out.println("Received Token: " + token);
            if (jwtProvider.isTokenValid(token)) {
                String mobileNumber = jwtProvider.getMobileNumberFromToken(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(mobileNumber, null, Collections.emptyList());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("Invalid or expired token.");
            }
        }

        filterChain.doFilter(request, response);
    }
}

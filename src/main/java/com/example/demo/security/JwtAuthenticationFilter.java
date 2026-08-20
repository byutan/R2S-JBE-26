package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Runs once per request
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Reads Authorization header
        String header =  request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Extracts token from "Bearer "
        String token = header.substring(7);

        // Verifying signature
        if (!jwtService.isValidToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
//
//        // user already authenticated pass
//        if(SecurityContextHolder.getContext().getAuthentication() != null) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        // Set auth for user
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken auth =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // request with auth user
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}

package com.example.demo.security;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) {
        // User in DB check
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        // Đổi từ Role sang GrantedAuthority
        Set< GrantedAuthority> authorities = new HashSet<>();
        if (user.getRoles() != null) {
            for(Role role : user.getRoles()) {
                // Thêm Tiền tố ROLE_
                if(role.getName() != null) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                }
                // Thêm các Authority của role
                if(role.getAuthorities() != null) {
                    role.getAuthorities()
                            .stream()
                            .filter(Objects::nonNull)
                            .map(Authority::getName)
                            .filter(Objects::nonNull)
                            .map(SimpleGrantedAuthority::new)
                            .forEach(authorities::add);
                }
            }
        }

        // Trả UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

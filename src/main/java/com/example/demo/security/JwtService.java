package com.example.demo.security;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;

@Service
public class JwtService {

    private final SecretKey signingKey;
    private final long expirationSeconds;
    private final String issuer;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-seconds:3600}") long expirationSeconds,
            @Value("${app.jwt.issuer}") String issuer
    ) {
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationSeconds = expirationSeconds;
        this.issuer = issuer;
    }

    // generate token
    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expirationSeconds);

        List<String> roles = user.getRoles() == null ? List.of() : user.getRoles().stream().map(Role::getName).filter(Objects::nonNull).toList();
        Set<String> authorities = new HashSet<>();
        if(user.getRoles() != null) {
            for(Role role : user.getRoles()) {
                if (role.getAuthorities() != null) {
                    for (Authority authority : role.getAuthorities()) {
                        if (authority != null && authority.getName() != null) {
                            authorities.add(authority.getName());
                        }
                    }
                }
            }
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("authorities", authorities);

        return Jwts.builder()
                .issuer(issuer)
                .subject(user.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claims(claims)
                .signWith(signingKey)
                .compact();
    }

    // validate token
    public boolean isValidToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // validate utils
    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey) // verify key
                .build()
                .parseSignedClaims(token) // verify token exp
                .getPayload(); // get payload from claims
    }

    public String extractUsername (String token) {
        return parseClaims(token).getSubject();
    }

    public long getExpirationSeconds(String token) {
        return parseClaims(token).getExpiration().toInstant().getEpochSecond();
    }
}
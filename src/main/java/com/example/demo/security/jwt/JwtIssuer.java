package com.example.demo.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties jwtProperties;

    public String issue(Request request) {
        var now = Instant.now();

        return JWT.create()
                .withSubject(String.valueOf(request.id))
                .withIssuedAt(now)
                .withExpiresAt(now.plus(jwtProperties.getTokenDuration()))
                .withClaim("email", request.getEmail())
                .withClaim("authorities", request.getAuthorities())
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));

    }

    @Getter
    @Builder
    public static class Request {
        private final Long id;
        private final String email;
        private final List<String> authorities;
    }
}

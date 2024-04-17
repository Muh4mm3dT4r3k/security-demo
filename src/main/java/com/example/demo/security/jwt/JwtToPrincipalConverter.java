package com.example.demo.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JwtToPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt) {
        var authorityList = getClaimOrEmptyList(jwt, "authorities")
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return UserPrincipal
                .builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .username(jwt.getClaim("email").asString())
                .authorities(authorityList)
                .build();
    }


    private List<String> getClaimOrEmptyList(DecodedJWT jwt, String claim) {
        if (jwt.getClaim(claim).isNull()) return List.of();
        return jwt.getClaim(claim).asList(String.class);
    }
}

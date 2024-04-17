package com.example.demo.security.jwt;

import com.example.demo.security.UserPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final UserPrincipal userPrincipal;

    public UserPrincipalAuthenticationToken(UserPrincipal userPrincipal) {
        super(userPrincipal.getAuthorities());
        this.userPrincipal = userPrincipal;
        setAuthenticated(true);
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userPrincipal;
    }
}

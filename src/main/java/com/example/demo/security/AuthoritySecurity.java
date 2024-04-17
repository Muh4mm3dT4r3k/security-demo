package com.example.demo.security;

import com.example.demo.entity.Authority;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class AuthoritySecurity implements GrantedAuthority {
    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.getAuthorityName();
    }
}

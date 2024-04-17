package com.example.demo.security;

import com.example.demo.entity.Authority;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class UserSecurityDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findUserByUsernameOrEmail(username).orElseThrow();
        Stream<Authority> authorityStream = user.getRoles().stream().flatMap(role -> role.getAuthorities().stream());

        return UserPrincipal
                .builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorityStream
                        .map(AuthoritySecurity::new)
                        .collect(Collectors.toList()))
                .build();
    }
}

package com.ocbc.les.frame.security.config;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Data
public class CustomAuthentication implements Authentication {
    private final String userId;
    private final String username;
    private final String password;
//    private final List<SimpleGrantedAuthority> authorities;
    private boolean authenticated = true;


    public CustomAuthentication(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.password = null;
//        this.authorities = roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
    }

    public CustomAuthentication(String userId, String username,String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
//        this.authorities = roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return username;
    }

}

package com.cricMaster.fantasyICL_backend.security;

import com.cricMaster.fantasyICL_backend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public UserPrincipal(Long id,
                         String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public static UserPrincipal create(User user) {
        // if you have roles/authorities on your User, map them here
        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList(),
                user.getIsActive()
        );
    }

    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return enabled; }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
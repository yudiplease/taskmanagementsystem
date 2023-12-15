package dev.yudiplease.config.security;

import dev.yudiplease.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static dev.yudiplease.entity.EntityStatus.BANNED;
import static dev.yudiplease.entity.EntityStatus.CONFIRMED;

@AllArgsConstructor
public class BaseUserDetails implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Objects.equals(BANNED, user.getEntityStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(CONFIRMED, user.getEntityStatus());
    }
}
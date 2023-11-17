package com.example.jwttutorial.services;

import com.example.jwttutorial.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;
    @JsonIgnore
    private String password;

    private Integer enable;

    private Integer account_non_lock;

    private Collection<? extends GrantedAuthority> authorities;

    private ResponseCookie jwtCookie;

    public UserDetailsImpl(Long id, String username, String password, Integer enable,
                           Integer account_non_lock, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.account_non_lock = account_non_lock;
        this.authorities = authorities;
    }

    public UserDetailsImpl() {

    }

    public UserDetailsImpl(Long id, String username, Integer enable, Integer account_non_lock) {
        this.id = id;
        this.username = username;
        this.enable = enable;
        this.account_non_lock = account_non_lock;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getUsersRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoles().getName()))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoles().getName()))
                .collect(Collectors.toList());
        System.out.println("Authorities: " + authorities);
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEnable(),
                user.getAccount_non_lock(),
                authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getAccount_non_lock() {
        return account_non_lock;
    }

    public void setAccount_non_lock(Integer account_non_lock) {
        this.account_non_lock = account_non_lock;
    }

    public ResponseCookie getJwtCookie() {
        return jwtCookie;
    }

    public void setJwtCookie(ResponseCookie jwtCookie) {
        this.jwtCookie = jwtCookie;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

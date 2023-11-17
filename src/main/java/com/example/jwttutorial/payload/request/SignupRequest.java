package com.example.jwttutorial.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String email;

    private Integer enable;

    private Integer account_non_lock;

    private Set<String> role;

    public SignupRequest() {

    }

    public SignupRequest(String username, String password, String email, Integer enable, Integer account_non_lock) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.account_non_lock = account_non_lock;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
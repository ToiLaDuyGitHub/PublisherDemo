package com.example.jwttutorial.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

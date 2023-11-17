package com.example.jwttutorial.payload.response;

import lombok.Getter;

import java.util.List;

@Getter
public class UserInfoResponse {
    private Long id;
    private String username;
    private Integer enable;
    private Integer account_non_lock;
    private List<String> roles;

    public UserInfoResponse() {

    }

    public UserInfoResponse(Long id, String username, Integer enable, Integer account_non_lock, List<String> roles) {
        this.id = id;
        this.username = username;
        this.enable = enable;
        this.account_non_lock = account_non_lock;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

package com.example.jwttutorial.payload.response;

import java.util.List;

public class AuthorInfoResponse {
    private Long id;
    private Integer version;
    private String firstName;
    private String lastName;

    private List<String> publications;
    public AuthorInfoResponse() {
    }

    public AuthorInfoResponse(Integer version, String firstName, String lastName) {
        this.version = version;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getPublications() {
        return publications;
    }

    public void setPublications(List<String> publications) {
        this.publications = publications;
    }
}

package com.example.jwttutorial.payload.response;

import java.util.List;

public class AuthorInfoResponse {
    private Long id;
    private Integer version;
    private String first_name;
    private String last_name;

    private List<String> publications;
    public AuthorInfoResponse() {
    }

    public AuthorInfoResponse(Integer version, String first_name, String last_name) {
        this.version = version;
        this.first_name = first_name;
        this.last_name = last_name;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<String> getPublications() {
        return publications;
    }

    public void setPublications(List<String> publications) {
        this.publications = publications;
    }
}

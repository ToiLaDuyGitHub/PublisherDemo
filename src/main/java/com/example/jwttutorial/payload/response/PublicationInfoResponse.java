package com.example.jwttutorial.payload.response;

import java.time.LocalDate;
import java.util.List;

public class PublicationInfoResponse {
    private Long id;
    private Integer version;
    private String title;
    private LocalDate publishing_date;
    private List<String> authors;

    public PublicationInfoResponse() {

    }

    public PublicationInfoResponse(Integer version, String title, LocalDate publishing_date) {
        this.version = version;
        this.title = title;
        this.publishing_date = publishing_date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishing_date() {
        return publishing_date;
    }

    public void setPublishing_date(LocalDate publishing_date) {
        this.publishing_date = publishing_date;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}

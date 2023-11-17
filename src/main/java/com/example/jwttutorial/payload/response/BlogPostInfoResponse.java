package com.example.jwttutorial.payload.response;

import java.time.LocalDate;

public class BlogPostInfoResponse {
    private Long id;
    private String url;
    private Integer version;
    private String title;
    private LocalDate publishing_date;

    public BlogPostInfoResponse() {

    }

    public BlogPostInfoResponse(String url, Integer version, String title, LocalDate publishing_date) {
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
